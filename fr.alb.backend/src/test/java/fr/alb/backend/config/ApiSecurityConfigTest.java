package fr.alb.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "ALB_ADMIN_PASSWORD=test-only-secret",
        "spring.flyway.enabled=false",
        "spring.datasource.url=jdbc:h2:mem:albsecuritytest;DB_CLOSE_DELAY=-1"
})
@AutoConfigureMockMvc
class ApiSecurityConfigTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void publicNewsReadDoesNotRequireCredentials() throws Exception {
        mvc.perform(get("/api/public/news")).andExpect(status().isOk());
    }

    @Test
    void csrfBootstrapWorksBeforeLogin() throws Exception {
        mvc.perform(get("/api/auth/csrf"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headerName").value("X-CSRF-TOKEN"))
                .andExpect(jsonPath("$.token").isNotEmpty());
    }


    @Test
    void browserCanLoginUsingRealCsrfTokenAndReuseSession() throws Exception {
        MvcResult bootstrap = mvc.perform(get("/api/auth/csrf"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpSession session = (MockHttpSession) bootstrap.getRequest().getSession(false);
        assertNotNull(session);
        String csrfToken = objectMapper.readTree(bootstrap.getResponse().getContentAsString())
                .get("token").asText();

        MvcResult loginResult = mvc.perform(post("/api/auth/login")
                .session(session)
                .header("X-CSRF-TOKEN", csrfToken)
                .param("username", "admin")
                .param("password", "test-only-secret"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpSession adminSession = (MockHttpSession) loginResult.getRequest().getSession(false);

        mvc.perform(get("/api/auth/me").session(adminSession))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin"));

        MvcResult csrfAfterLogin = mvc.perform(get("/api/auth/csrf").session(adminSession))
                .andExpect(status().isOk())
                .andReturn();
        String freshCsrf = objectMapper.readTree(csrfAfterLogin.getResponse().getContentAsString())
                .get("token").asText();

        mvc.perform(post("/api/admin/news")
                .session(adminSession)
                .header("X-CSRF-TOKEN", freshCsrf)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void anonymousAdminReadsAndProfileAreRejected() throws Exception {
        mvc.perform(get("/api/news")).andExpect(status().isUnauthorized());
        mvc.perform(get("/api/admin/news")).andExpect(status().isUnauthorized());
        mvc.perform(get("/api/auth/me")).andExpect(status().isUnauthorized());
    }

    @Test
    void loginRequiresCsrfToken() throws Exception {
        mvc.perform(post("/api/auth/login")
                .param("username", "admin")
                .param("password", "test-only-secret"))
                .andExpect(status().isForbidden());
    }

    @Test
    void invalidLoginIsRejected() throws Exception {
        mvc.perform(post("/api/auth/login")
                .with(csrf())
                .param("username", "admin")
                .param("password", "wrong"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void basicAuthorizationAloneNoLongerGrantsAdminAccess() throws Exception {
        mvc.perform(get("/api/admin/news")
                .with(httpBasic("admin", "test-only-secret")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void sessionLoginAllowsAdminReadsAndProfile() throws Exception {
        MockHttpSession session = login();

        mvc.perform(get("/api/admin/news").session(session))
                .andExpect(status().isOk());
        mvc.perform(get("/api/auth/me").session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin"));
    }

    @Test
    void csrfIsRequiredForAuthenticatedWrites() throws Exception {
        MockHttpSession session = login();

        mvc.perform(post("/api/admin/news")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isForbidden());

        mvc.perform(post("/api/admin/news")
                .session(session)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void anonymousWriteWithCsrfIsRejected() throws Exception {
        mvc.perform(post("/api/admin/news")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isUnauthorized());
        mvc.perform(patch("/api/admin/news/1/publish")
                .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void oversizedAdminNewsPageReturnsStructuredBadRequest() throws Exception {
        mvc.perform(get("/api/admin/news/page")
                .session(login())
                .param("page", "0")
                .param("size", "101"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("La taille de page maximale est 100."));
    }

    @Test
    void logoutInvalidatesSession() throws Exception {
        MockHttpSession session = login();

        mvc.perform(post("/api/auth/logout")
                .session(session)
                .with(csrf()))
                .andExpect(status().isNoContent());

        assertTrue(session.isInvalid());
    }

    private MockHttpSession login() throws Exception {
        MvcResult loginResult = mvc.perform(post("/api/auth/login")
                .with(csrf())
                .param("username", "admin")
                .param("password", "test-only-secret"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authenticated").value(true))
                .andReturn();

        MockHttpSession session = (MockHttpSession) loginResult.getRequest().getSession(false);
        assertNotNull(session, "La connexion doit créer une session HTTP.");
        return session;
    }
}

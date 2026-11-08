package fr.alb.backend.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(properties = {"ALB_ADMIN_PASSWORD=test-only-secret", "spring.flyway.enabled=false", "spring.datasource.url=jdbc:h2:mem:albsecuritytest;DB_CLOSE_DELAY=-1"})
@AutoConfigureMockMvc
class ApiSecurityConfigTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void publicNewsReadDoesNotRequireCredentials() throws Exception {
        mvc.perform(get("/api/public/news")).andExpect(status().isOk());
    }

    @Test
    void anonymousAdminNewsListIsRejected() throws Exception {
        mvc.perform(get("/api/news")).andExpect(status().isUnauthorized());
        mvc.perform(get("/api/admin/news")).andExpect(status().isUnauthorized());
        mvc.perform(get("/api/news/1")).andExpect(status().isUnauthorized());
    }

    @Test
    void authenticatedAdminCanReadNewsList() throws Exception {
        mvc.perform(get("/api/admin/news")
                .header("Authorization", basic("admin", "test-only-secret")))
                .andExpect(status().isOk());
    }

    @Test
    void anonymousPublicationChangeIsRejected() throws Exception {
        mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch("/api/admin/news/1/publish"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void anonymousWriteIsRejected() throws Exception {
        mvc.perform(post("/api/news")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void invalidCredentialsAreRejected() throws Exception {
        mvc.perform(delete("/api/news/999999")
                .header("Authorization", basic("admin", "wrong")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void authenticatedAdminReachesValidationLayer() throws Exception {
        mvc.perform(post("/api/news")
                .header("Authorization", basic("admin", "test-only-secret"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }


    @Test
    void oversizedAdminNewsPageReturnsStructuredBadRequest() throws Exception {
        mvc.perform(get("/api/admin/news/page")
                .param("page", "0")
                .param("size", "101")
                .header("Authorization", basic("admin", "test-only-secret")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("La taille de page maximale est 100."));
    }

    @Test
    void anonymousOversizedAdminNewsPageIsStillProtected() throws Exception {
        mvc.perform(get("/api/admin/news/page")
                .param("page", "0")
                .param("size", "101"))
                .andExpect(status().isUnauthorized());
    }

    private static String basic(String username, String password) {
        String value = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }
}

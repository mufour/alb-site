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

@SpringBootTest(properties = "ALB_ADMIN_PASSWORD=test-only-secret")
@AutoConfigureMockMvc
class ApiSecurityConfigTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void publicNewsReadDoesNotRequireCredentials() throws Exception {
        mvc.perform(get("/api/news")).andExpect(status().isOk());
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

    private static String basic(String username, String password) {
        String value = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }
}

package fr.alb.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.alb.backend.dto.response.NewsResponse;
import fr.alb.backend.exception.GlobalExceptionHandler;
import fr.alb.backend.exception.ResourceNotFoundException;
import fr.alb.backend.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NewsController.class)
class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NewsService newsService;

    @Test
    void createShouldReturn400WhenRequiredFieldsAreMissing() throws Exception {
        mockMvc.perform(post("/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.validationErrors.title").value("Le titre est obligatoire."))
                .andExpect(jsonPath("$.validationErrors.content").value("Le contenu est obligatoire."));
    }

    @Test
    void getByIdShouldReturn404WhenNewsDoesNotExist() throws Exception {
        when(newsService.getById(666L))
                .thenThrow(new ResourceNotFoundException("L'actualité n'a pas été trouvée."));

        mockMvc.perform(get("/api/news/666"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    void getBySubtitleShouldUseSubtitleEndpoint() throws Exception {
        NewsResponse response = new NewsResponse(1L, "Titre", "Sous-titre", "Contenu", 1L, true);
        when(newsService.getBySubtitle("Sous-titre")).thenReturn(response);

        mockMvc.perform(get("/api/news/subtitle/{subtitle}", "Sous-titre"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.subtitle").value("Sous-titre"));

        verify(newsService).getBySubtitle("Sous-titre");
    }
}

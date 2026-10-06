package fr.alb.backend.controller;

import fr.alb.backend.dto.response.NewsResponse;
import fr.alb.backend.service.PublicContentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublicContentController.class)
class PublicContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicContentService publicContentService;

    @Test
    void getNewsShouldReturnOnlyContentProvidedByPublishedNewsService() throws Exception {
        NewsResponse published = new NewsResponse(1L, "Publiée", "Sous-titre", "Contenu", 1L, true);
        when(publicContentService.getPublishedNews()).thenReturn(List.of(published));

        mockMvc.perform(get("/api/public/news"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].published").value(true))
                .andExpect(jsonPath("$[0].id").value(1));
    }
}

package fr.alb.backend.service;

import fr.alb.backend.mapper.NewsMapper;
import fr.alb.backend.model.entity.News;
import fr.alb.backend.repository.MediaRepository;
import fr.alb.backend.repository.NewsRepository;
import fr.alb.backend.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NewsServicePublicationTest {

    private final NewsRepository repository = mock(NewsRepository.class);
    private final NewsService service = new NewsService(repository, new NewsMapper(), mock(MediaRepository.class));

    @Test
    void publishExistingDraft() {
        News draft = new News();
        draft.setPublished(false);
        when(repository.findById(1L)).thenReturn(Optional.of(draft));
        when(repository.save(any(News.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertTrue(service.setPublished(1L, true).getPublished());
        verify(repository).save(draft);
    }

    @Test
    void unpublishExistingNews() {
        News published = new News();
        published.setPublished(true);
        when(repository.findById(2L)).thenReturn(Optional.of(published));
        when(repository.save(any(News.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertFalse(service.setPublished(2L, false).getPublished());
        verify(repository).save(published);
    }

    @Test
    void missingNewsCannotBePublished() {
        when(repository.findById(404L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.setPublished(404L, true));
        verify(repository, never()).save(any());
    }
}

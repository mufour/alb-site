package fr.alb.backend.service;

import fr.alb.backend.mapper.NewsMapper;
import fr.alb.backend.model.entity.News;
import fr.alb.backend.repository.MediaRepository;
import fr.alb.backend.repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NewsServicePaginationTest {

    @Test
    void getPageDelegatesToRepositoryAndPreservesMetadata() {
        NewsRepository repository = mock(NewsRepository.class);
        NewsMapper mapper = mock(NewsMapper.class);
        MediaRepository mediaRepository = mock(MediaRepository.class);
        NewsService service = new NewsService(repository, mapper, mediaRepository);
        var pageable = PageRequest.of(1, 10);
        when(repository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(new News()), pageable, 21));

        var page = service.getPage(pageable);

        assertEquals(1, page.getNumber());
        assertEquals(10, page.getSize());
        assertEquals(21, page.getTotalElements());
        verify(repository).findAll(pageable);
        verify(mapper).toResponse(any(News.class));
    }
}

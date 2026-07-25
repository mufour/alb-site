package fr.alb.backend.service;

import org.springframework.stereotype.Service;
import fr.alb.backend.mapper.NewsMapper;
import fr.alb.backend.model.entity.News;
import fr.alb.backend.repository.NewsRepository;
import fr.alb.backend.dto.request.CreateNewsRequest;
import fr.alb.backend.dto.request.UpdateNewsRequest;
import fr.alb.backend.dto.response.NewsResponse;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }

    public List<NewsResponse> getAll() {
        return newsRepository.findAll()
                .stream()
                .map(newsMapper::toResponse)
                .toList();
    }

    public NewsResponse getById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La news n'a pas été trouvé"));
        return newsMapper.toResponse(news);
    }

    public NewsResponse getByTitle(String title) {
        News news = newsRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("La news n'a pas été trouvé"));
        return newsMapper.toResponse(news);
    }

    public NewsResponse getBySubtitle(String subtitle) {
        News news = newsRepository.findByTitle(subtitle)
                .orElseThrow(() -> new RuntimeException("La news n'a pas été trouvé"));
        return newsMapper.toResponse(news);
    }

    public NewsResponse create(CreateNewsRequest request) {
        News news = newsMapper.toEntity(request);
        News saved = newsRepository.save(news);
        return newsMapper.toResponse(saved);
    }

    public NewsResponse update(Long id, UpdateNewsRequest request) {
        News news = newsRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("La news n'a pas été trouvé"));
            newsMapper.updateEntity(request, news);
            News saved = newsRepository.save(news);
            return newsMapper.toResponse(saved);
    }

    public void delete(Long id) {
        News news = newsRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("La news n'a pas été trouve"));
        newsRepository.delete(news);
    }
}
package fr.alb.backend.mapper;

import org.springframework.stereotype.Component;

import fr.alb.backend.dto.request.CreateNewsRequest;
import fr.alb.backend.dto.request.UpdateNewsRequest;
import fr.alb.backend.dto.response.NewsResponse;
import fr.alb.backend.model.entity.News;

@Component
public class NewsMapper {
    
    public News toEntity(CreateNewsRequest request) {

        News news = new News();

        news.setTitle(request.getTitle());
        news.setSubtitle(request.getSubtitle());
        news.setContent(request.getContent());
        news.setPublished(request.getPublished());

        return news;
    }

    public NewsResponse toResponse(News news) {

        NewsResponse response = new NewsResponse();

        response.setId(news.getId());
        response.setTitle(news.getTitle());
        response.setSubtitle(news.getSubtitle());
        response.setContent(news.getContent());
        response.setPublished(news.getPublished());

        if (news.getImage() != null) {
            response.setImageId(news.getImage().getId());
    }
        return response;
    }

    public void updateEntity(UpdateNewsRequest request, News news) {

        news.setTitle(request.getTitle());
        news.setSubtitle(request.getSubtitle());
        news.setContent(request.getContent());
        news.setPublished(request.getPublished());        
    }

}

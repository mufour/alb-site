package fr.alb.backend.mapper;

import org.springframework.stereotype.Component;

import fr.alb.backend.dto.request.CreatePageRequest;
import fr.alb.backend.dto.request.UpdatePageRequest;
import fr.alb.backend.dto.response.PageResponse;
import fr.alb.backend.model.entity.Page;

@Component
public class PageMapper {
    
    public Page toEntity(CreatePageRequest request) {

        Page page = new Page();

        page.setTitle(request.getTitle());
        page.setSlug(request.getSlug());
        page.setContent(request.getContent());

        return page;
    }

    public PageResponse toResponse (Page page) {

        PageResponse response = new PageResponse();

        response.setId(page.getId());
        response.setTitle(page.getTitle());
        response.setSlug(page.getSlug());
        response.setContent(page.getContent());

        return response;
    }

    public void updateEntity(UpdatePageRequest request, Page page) {
        
        page.setTitle(request.getTitle());
        page.setSlug(request.getSlug());
        page.setContent(request.getContent());
    }
}

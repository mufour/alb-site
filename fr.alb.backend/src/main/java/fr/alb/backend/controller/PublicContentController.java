package fr.alb.backend.controller;

import fr.alb.backend.dto.response.BannerImageResponse;
import fr.alb.backend.dto.response.EventResponse;
import fr.alb.backend.dto.response.NewsResponse;
import fr.alb.backend.dto.response.PartnerResponse;
import fr.alb.backend.dto.response.TeamMemberResponse;
import fr.alb.backend.service.PublicContentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicContentController {

    private final PublicContentService publicContentService;

    public PublicContentController(PublicContentService publicContentService) {
        this.publicContentService = publicContentService;
    }

    @GetMapping("/banner-images")
    public List<BannerImageResponse> getBannerImages() { return publicContentService.getActiveBannerImages(); }

    @GetMapping("/news")
    public List<NewsResponse> getNews() { return publicContentService.getPublishedNews(); }

    @GetMapping("/events")
    public List<EventResponse> getEvents() { return publicContentService.getPublishedEvents(); }

    @GetMapping("/partners")
    public List<PartnerResponse> getPartners() { return publicContentService.getPartners(); }

    @GetMapping("/team-members")
    public List<TeamMemberResponse> getTeamMembers() { return publicContentService.getTeamMembers(); }
}

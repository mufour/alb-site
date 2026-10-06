package fr.alb.backend.service;

import fr.alb.backend.dto.response.*;
import fr.alb.backend.mapper.*;
import fr.alb.backend.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicContentService {

    private final BannerImageRepository bannerImageRepository;
    private final EventRepository eventRepository;
    private final NewsRepository newsRepository;
    private final PartnerRepository partnerRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final BannerImageMapper bannerImageMapper;
    private final EventMapper eventMapper;
    private final NewsMapper newsMapper;
    private final PartnerMapper partnerMapper;
    private final TeamMemberMapper teamMemberMapper;

    public PublicContentService(BannerImageRepository bannerImageRepository, EventRepository eventRepository,
            NewsRepository newsRepository, PartnerRepository partnerRepository,
            TeamMemberRepository teamMemberRepository, BannerImageMapper bannerImageMapper,
            EventMapper eventMapper, NewsMapper newsMapper, PartnerMapper partnerMapper,
            TeamMemberMapper teamMemberMapper) {
        this.bannerImageRepository = bannerImageRepository;
        this.eventRepository = eventRepository;
        this.newsRepository = newsRepository;
        this.partnerRepository = partnerRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.bannerImageMapper = bannerImageMapper;
        this.eventMapper = eventMapper;
        this.newsMapper = newsMapper;
        this.partnerMapper = partnerMapper;
        this.teamMemberMapper = teamMemberMapper;
    }

    public List<BannerImageResponse> getActiveBannerImages() {
        return bannerImageRepository.findByActiveTrueOrderByDisplayOrderAsc().stream().map(bannerImageMapper::toResponse).toList();
    }

    public List<NewsResponse> getPublishedNews() {
        return newsRepository.findByPublishedTrueOrderByCreatedAtDesc().stream().map(newsMapper::toResponse).toList();
    }

    public List<EventResponse> getPublishedEvents() {
        return eventRepository.findByPublishedTrueOrderByStartDateDesc().stream().map(eventMapper::toResponse).toList();
    }

    public List<PartnerResponse> getPartners() {
        return partnerRepository.findAllByOrderByDisplayOrderAsc().stream().map(partnerMapper::toResponse).toList();
    }

    public List<TeamMemberResponse> getTeamMembers() {
        return teamMemberRepository.findAllByOrderByDisplayOrderAsc().stream().map(teamMemberMapper::toResponse).toList();
    }
}

package fr.alb.backend.service;

import org.springframework.stereotype.Service;
import fr.alb.backend.mapper.TeamMemberMapper;
import fr.alb.backend.model.entity.Media;
import fr.alb.backend.model.entity.TeamMember;
import fr.alb.backend.repository.MediaRepository;
import fr.alb.backend.repository.TeamMemberRepository;
import fr.alb.backend.dto.request.CreateTeamMemberRequest;
import fr.alb.backend.dto.request.UpdateTeamMemberRequest;
import fr.alb.backend.dto.response.TeamMemberResponse;

import java.util.List;

@Service
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final TeamMemberMapper teamMemberMapper;
    private final MediaRepository mediaRepository;

    public TeamMemberService(TeamMemberRepository teamMemberRepository, TeamMemberMapper teamMemberMapper, MediaRepository mediaRepository) {
        this.teamMemberRepository = teamMemberRepository;
        this.teamMemberMapper = teamMemberMapper;
        this.mediaRepository = mediaRepository;
    }

    public List<TeamMemberResponse> getAll() {
        return teamMemberRepository.findAll()
                .stream()
                .map(teamMemberMapper::toResponse)
                .toList();
    }

    public TeamMemberResponse getById(Long id) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Le membre de l'équipe n'a pas été trouvé"));
        return teamMemberMapper.toResponse(teamMember);
    }

    public List<TeamMemberResponse> getByRole(String role) {
        return teamMemberRepository.findByRole(role)
                .stream()
                .map(teamMemberMapper::toResponse)
                .toList();
    }

    public TeamMemberResponse create(CreateTeamMemberRequest request) {
        TeamMember teamMember = teamMemberMapper.toEntity(request);
        if (request.getImageId() != null) {
            Media media = mediaRepository.findById(request.getImageId())
                    .orElseThrow(() -> new RuntimeException("Le média n'a pas été trouvé"));
            teamMember.setImage(media);
        }
        TeamMember saved = teamMemberRepository.save(teamMember);
        return teamMemberMapper.toResponse(saved);
    }

    public TeamMemberResponse update(Long id, UpdateTeamMemberRequest request) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Le membre de l'équipe n'a pas été trouvé"));
        teamMemberMapper.updateEntity(request, teamMember);
        if (request.getImageId() != null) {
            Media media = mediaRepository.findById(request.getImageId())
                    .orElseThrow(() -> new RuntimeException("Le média n'a pas été trouvé"));
            teamMember.setImage(media);
        } else {
            teamMember.setImage(null);
        }
        TeamMember saved = teamMemberRepository.save(teamMember);
        return teamMemberMapper.toResponse(saved);
    }

    public void delete(Long id) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Le membre de l'équipe n'a pas été trouvé"));
        teamMemberRepository.delete(teamMember);
    }
}
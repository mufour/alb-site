package fr.alb.backend.service;

import fr.alb.backend.dto.request.CreateTeamMemberRequest;
import fr.alb.backend.dto.request.UpdateTeamMemberRequest;
import fr.alb.backend.dto.response.TeamMemberResponse;
import fr.alb.backend.exception.ResourceNotFoundException;
import fr.alb.backend.mapper.TeamMemberMapper;
import fr.alb.backend.model.entity.TeamMember;
import fr.alb.backend.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberService {
    private final TeamMemberRepository teamMemberRepository;
    private final TeamMemberMapper teamMemberMapper;

    public TeamMemberService(TeamMemberRepository teamMemberRepository, TeamMemberMapper teamMemberMapper) {
        this.teamMemberRepository = teamMemberRepository;
        this.teamMemberMapper = teamMemberMapper;
    }

    public List<TeamMemberResponse> getAll() {
        return teamMemberRepository.findAll().stream().map(teamMemberMapper::toResponse).toList();
    }

    public TeamMemberResponse getById(Long id) {
        return teamMemberMapper.toResponse(findTeamMember(id));
    }

    public List<TeamMemberResponse> getByRole(String role) {
        return teamMemberRepository.findByRole(role).stream().map(teamMemberMapper::toResponse).toList();
    }

    public TeamMemberResponse create(CreateTeamMemberRequest request) {
        return teamMemberMapper.toResponse(teamMemberRepository.save(teamMemberMapper.toEntity(request)));
    }

    public TeamMemberResponse update(Long id, UpdateTeamMemberRequest request) {
        TeamMember teamMember = findTeamMember(id);
        teamMemberMapper.updateEntity(request, teamMember);
        return teamMemberMapper.toResponse(teamMemberRepository.save(teamMember));
    }

    public void delete(Long id) {
        teamMemberRepository.delete(findTeamMember(id));
    }

    private TeamMember findTeamMember(Long id) {
        return teamMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le membre de l'équipe n'a pas été trouvé."));
    }
}

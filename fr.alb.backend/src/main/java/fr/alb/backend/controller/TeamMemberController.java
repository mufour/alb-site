package fr.alb.backend.controller;

import jakarta.validation.Valid;

import fr.alb.backend.dto.request.CreateTeamMemberRequest;
import fr.alb.backend.dto.request.UpdateTeamMemberRequest;
import fr.alb.backend.dto.response.TeamMemberResponse;
import fr.alb.backend.service.TeamMemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team-members")
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    public TeamMemberController(TeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
    }

    @GetMapping
    public List<TeamMemberResponse> getAll() {
        return teamMemberService.getAll();
    }

    @GetMapping("/{id}")
    public TeamMemberResponse getById(@PathVariable Long id) {
        return teamMemberService.getById(id);
    }

    @GetMapping("/role/{role}")
    public List<TeamMemberResponse> getByRole(@PathVariable String role) {
        return teamMemberService.getByRole(role);
    }

    @PostMapping
    public TeamMemberResponse create(@Valid @RequestBody CreateTeamMemberRequest request) {
        return teamMemberService.create(request);
    }

    @PutMapping("/{id}")
    public TeamMemberResponse update(@PathVariable Long id, @Valid @RequestBody UpdateTeamMemberRequest request) {
        return teamMemberService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teamMemberService.delete(id);
    }
}
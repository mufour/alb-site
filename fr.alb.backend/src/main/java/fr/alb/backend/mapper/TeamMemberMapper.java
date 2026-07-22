package fr.alb.backend.mapper;

import org.springframework.stereotype.Component;

import fr.alb.backend.dto.request.CreateTeamMemberRequest;
import fr.alb.backend.dto.request.UpdateTeamMemberRequest;
import fr.alb.backend.dto.response.TeamMemberResponse;
import fr.alb.backend.model.entity.TeamMember;

@Component
public class TeamMemberMapper {

    public TeamMember toEntity(CreateTeamMemberRequest request) {

        TeamMember teamMember = new TeamMember();

        teamMember.setFirstName(request.getFirstName());
        teamMember.setLastName(request.getLastName());
        teamMember.setRole(request.getRole());
        teamMember.setBio(request.getBio());
        teamMember.setDisplayOrder(request.getDisplayOrder());

        return teamMember;
    }

    public TeamMemberResponse toResponse(TeamMember teamMember) {

        TeamMemberResponse response = new TeamMemberResponse();

        response.setId(teamMember.getId());
        response.setFirstName(teamMember.getFirstName());
        response.setLastName(teamMember.getLastName());
        response.setRole(teamMember.getRole());
        response.setBio(teamMember.getBio());
        response.setDisplayOrder(teamMember.getDisplayOrder());

        if (teamMember.getImage() != null) {
            response.setImageId(teamMember.getImage().getId());
        }

        return response;
    }

    public void updateEntity(UpdateTeamMemberRequest request, TeamMember teamMember) {

        teamMember.setFirstName(request.getFirstName());
        teamMember.setLastName(request.getLastName());
        teamMember.setRole(request.getRole());
        teamMember.setBio(request.getBio());
        teamMember.setDisplayOrder(request.getDisplayOrder());
    }

}
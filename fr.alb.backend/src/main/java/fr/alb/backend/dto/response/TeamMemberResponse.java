package fr.alb.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamMemberResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String role;

    private String bio;

    private Long imageId;

    private Integer displayOrder;
}

package fr.alb.backend.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamMemberRequest {
 
    private String firstName;

    private String lastName;

    private String role;

    private String bio;

    private Long imageId;

    private Integer displayOrder;
}

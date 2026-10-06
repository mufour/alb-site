package fr.alb.backend.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeamMemberRequest {
    
    @NotBlank(message = "Le prénom est obligatoire.")
    private String firstName;
    
    @NotBlank(message = "Le nom est obligatoire.")
    private String lastName;

    @NotBlank(message = "Le rôle est obligatoire.")
    private String role;

    private String bio;

    private Integer displayOrder;
}

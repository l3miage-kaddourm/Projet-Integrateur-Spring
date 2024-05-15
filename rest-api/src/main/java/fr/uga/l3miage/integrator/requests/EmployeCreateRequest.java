package fr.uga.l3miage.integrator.requests;

import fr.uga.l3miage.integrator.enums.Emploi;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeCreateRequest {

    private String trigramme;

    private String prenom;

    private String nom;

    private String photo;

    private String telephone;

    private String email;

    @Enumerated(EnumType.STRING)
    private Emploi emploi;

}

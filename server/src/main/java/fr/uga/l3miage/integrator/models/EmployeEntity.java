package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.Emploi;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeEntity {

    @Id
    private String trigramme;

    private String email;

    private String prenom;

    private String nom;

    private String photo;

    private String telephone;

    @Enumerated(EnumType.STRING)
    private Emploi emploi;

    @ManyToOne
    @JoinColumn(name = "tournee_id")
    private TourneeEntity tournee;

    @ManyToOne
    @JoinColumn(name = "entrepot_id")
    private EntrepotEntity entrepot;
}

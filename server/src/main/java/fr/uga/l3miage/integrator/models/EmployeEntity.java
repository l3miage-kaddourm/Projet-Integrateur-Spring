package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.Emploi;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class EmployeEntity {

    @Id
    private String trigramme;

    private String email;

    private String nom;

    private String prenom;

    private String img;

    private String telephone;


    @Enumerated(EnumType.STRING)
    private Emploi emploi;

    @ManyToMany
    private Set<TourneeEntity> tournees;
}

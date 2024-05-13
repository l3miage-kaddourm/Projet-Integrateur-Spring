package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourneeEntity {
    @Id
    private String reference;

    private EtatsDeTournee etat;

    private String lettre;

    private Double montant;

//    private Integer tdmTheorique;
//
//    private Integer tdmEffective;

    private Double distanceAparcourir;



    @OneToMany(mappedBy = "tournee")
    private Set<LivraisonEntity> livraisons;

    @ManyToOne(fetch = FetchType.EAGER)
    private JourneeEntity journee;

    ////    @ManyToMany(mappedBy = "tournees")
////    private Set<EmployeEntity> employes;

    @ManyToOne
    private CamionEntity camion;

}

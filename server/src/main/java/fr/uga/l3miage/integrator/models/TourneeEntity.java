package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourneeEntity {
    @Id
    private String reference;
    private EtatsDeTournee etat;
    private String lettre;
    private Double montant;
    private Integer tdmTheorique;
    private Integer tdmEffective;
    private Double distanceAparcourir;
    private Double distanceDeRetour;
}

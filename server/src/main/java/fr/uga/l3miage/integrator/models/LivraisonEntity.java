package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonEntity {
    @Id
    private String reference;
    private EtatsDeLivraison etat;
    private Double montant;
    private Double distanceAparcourir;
    private Integer tdpAlAller;
    private Integer tdpTheorique;
    private Integer tdmTheorique;
    private Time heureDeLivraison;
    private LocalDateTime heureDeLivraisonEffective;
    private Integer tdmEffectif;
}

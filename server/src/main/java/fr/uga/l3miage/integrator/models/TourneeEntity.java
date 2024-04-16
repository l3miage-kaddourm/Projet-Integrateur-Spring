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

    @Column(name = "tdp_theorique")
    private Integer tdmTheorique;

    @Column(name = "tdm_effective")
    private Integer tdmEffective;

    @Column(name = "distance_a_parcourir")
    private Double distanceAparcourir;

    @Column(name = "distance_de_retour")
    private Double distanceDeRetour;

    @OneToMany(mappedBy = "tournee")
    private Set<LivraisonEntity> livraisons;

    @ManyToOne
    private JourneeEntity journee;


    @PrePersist
    @PreUpdate
    public void calculerAttributs() {
        if (livraisons != null) {
            this.montant = livraisons.stream()
                    .mapToDouble(LivraisonEntity::getMontant)
                    .sum();
            this.tdmTheorique = livraisons.stream()
                    .mapToInt(LivraisonEntity::getTdmTheorique)
                    .sum();
            this.tdmEffective= livraisons.stream()
                    .mapToInt(LivraisonEntity::getTdmEffectif)
                    .sum();
            this.distanceAparcourir= livraisons.stream()
                    .mapToDouble(LivraisonEntity::getDistanceAparcourir)
                    .sum();
            //TODO : calculer distance de retour
        }
    }
}

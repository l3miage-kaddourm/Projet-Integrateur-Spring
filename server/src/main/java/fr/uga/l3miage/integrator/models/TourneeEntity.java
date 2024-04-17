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

    private Integer tdmTheorique;

    private Integer tdmEffective;

    private Double distanceAparcourir;

    private Double distanceDeRetour;

    @OneToMany(mappedBy = "tournee")
    private Set<LivraisonEntity> livraisons;

    @ManyToOne
    private JourneeEntity journee;

    @ManyToMany(mappedBy = "tournees")
    private Set<EmployeEntity> employes;


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

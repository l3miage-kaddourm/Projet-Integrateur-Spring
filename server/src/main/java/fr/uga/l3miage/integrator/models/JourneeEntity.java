package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JourneeEntity {
    @Id
    private String reference;

    @Enumerated(EnumType.STRING)
    private EtatsDeJournee etat;

    private Date date;

    private Double distanceAParcourir;

    private Double montant;

    private Integer tdmTheorique;

    @OneToMany(mappedBy = "journee")
    private Set<TourneeEntity> tournees;

    @PrePersist
    @PreUpdate
    public void calculerAttributs() {
        if (tournees != null) {
            this.montant = tournees.stream()
                    .mapToDouble(TourneeEntity::getMontant)
                    .sum();
            this.tdmTheorique = tournees.stream()
                    .mapToInt(TourneeEntity::getTdmTheorique)
                    .sum();
            this.distanceAParcourir = tournees.stream()
                    .mapToDouble(TourneeEntity::getDistanceAparcourir)
                    .sum();
            //TODO: calculer reference
        }
    }
}

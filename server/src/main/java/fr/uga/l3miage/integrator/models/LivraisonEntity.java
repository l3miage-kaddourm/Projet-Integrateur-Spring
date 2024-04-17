package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonEntity {
    @Id
    private String reference;

    @Enumerated(EnumType.STRING)
    private EtatsDeLivraison etat;

    private Double montant;

    private Double distanceAparcourir;

    private Integer tdpAlAller;

    private Integer tdpTheorique;

    private Integer tdmTheorique;

    private Time heureDeLivraison;

    private LocalDateTime heureDeLivraisonEffective;

    private Integer tdmEffectif;

    @OneToMany(mappedBy = "livraison")
    private Set<CommandeEntity> commandes;

    @ManyToOne
    private TourneeEntity tournee;

    @PrePersist
    @PreUpdate
    public void calculerAttributs() {
        if (commandes != null) {
            this.montant = commandes.stream()
                    .mapToDouble(CommandeEntity::getMontant)
                    .sum();
            this.tdmTheorique = commandes.stream()
                    .mapToInt(CommandeEntity::getTdmTheorique)
                    .sum();
        }
    }

    //TODO:calculerheureDeLivraison() , claculerreferece()

}

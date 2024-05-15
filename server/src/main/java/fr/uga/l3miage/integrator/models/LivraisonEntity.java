package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
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

    private Time heureDeLivraison;

    private Time heureDeLivraisonEffective;

    private Integer tdmTheorique;

    private Integer tdmEffectif;

    @OneToMany(mappedBy = "livraison", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CommandeEntity> commandes;

    @ManyToOne
    private TourneeEntity tournee;


}

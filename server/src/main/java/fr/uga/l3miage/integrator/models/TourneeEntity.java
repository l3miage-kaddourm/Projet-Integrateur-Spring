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

    @Enumerated(EnumType.STRING)
    private EtatsDeTournee etat;

    private String lettre;

    private Double montant;

    private Double distanceAparcourir;

    @OneToMany(mappedBy = "tournee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LivraisonEntity> livraisons;

    @ManyToOne
    private JourneeEntity journee;

    @ManyToOne
    private CamionEntity camion;
}

package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeEntity {

    @Id
    private String reference;

    @Enumerated(EnumType.STRING)
    private EtatsDeCommande etat;

    private LocalDateTime dateDeCreation;

    private Integer note;

    private String commentaire;

    private double montant;

    private Integer tddTheorique;

    private Integer tdmTheorique;

    private LocalDateTime dateDeLivraisonEffective;

    private Integer dureeDeLivraison;

    @ManyToOne
    private LivraisonEntity livraison;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LigneEntity> lignes ;

    @ManyToOne
    @JoinColumn(name = "client_email")
    private ClientEntity client;



}

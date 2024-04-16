package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.enums.Note;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class CommandeEntity {

    @Id
    private String reference;


    @Enumerated(EnumType.STRING)
    private EtatsDeCommande etat;

    @Column(name = "date_de_validation")
    private LocalDateTime dateDeValidation;

    @Enumerated(EnumType.ORDINAL)
    private Note note;


    private String commentaire;

    private double montant;

    @Column(name = "tdp_theorique")
    private Integer tddTheorique;

    @Column(name = "tdm_theorique")
    private Integer tdmTheorique;

    @Column(name = "date_de_livraison")
    private LocalDateTime dateDeLivraisonEffective;

    @Column(name = "duree_de_livraison")
    private Integer dureeDeLivraison;

    @ManyToOne
    private LivraisonEntity livraison;

}

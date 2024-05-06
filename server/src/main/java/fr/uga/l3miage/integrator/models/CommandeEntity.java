package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.enums.Note;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
public class CommandeEntity {

    @Id
    private String reference;


    @Enumerated(EnumType.STRING)
    private EtatsDeCommande etat;

    private LocalDateTime dateDeValidation;

    @Enumerated(EnumType.ORDINAL)
    private Note note;


    private String commentaire;

    private double montant;

    private Integer tddTheorique;

    private Integer tdmTheorique;

    private LocalDateTime dateDeLivraisonEffective;

    private Integer dureeDeLivraison;

//    @ManyToOne
//    private LivraisonEntity livraison;


//    @OneToMany(mappedBy = "commande")
//    private Set<LigneEntity> lignesProduits;

//    @ManyToOne
//    private ClientEntity client;

//    @ManyToMany
//    private Set<ProduitEntity> produits;

}

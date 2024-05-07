package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.enums.Note;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private LocalDateTime dateDeCreation ;

    private Integer note;


    private String commentaire;

    private double montant;

    private Integer tddTheorique;

    private Integer tdmTheorique;

    private LocalDateTime dateDeLivraisonEffective;

    private Integer dureeDeLivraison;

    @ManyToOne
    private LivraisonEntity livraison;


//    @OneToMany(mappedBy = "commande")
//    private Set<LigneEntity> lignesProduits;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_email")
    private ClientEntity client;

//    @ManyToMany
//    private Set<ProduitEntity> produits;


    public String getClient_email() {
        return this.client.getEmail();
    }

}

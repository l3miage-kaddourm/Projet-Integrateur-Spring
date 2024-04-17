package fr.uga.l3miage.integrator.models;


import javax.persistence.*;

@Entity
public class LigneEntity {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private CommandeEntity commande;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private ProduitEntity produit;

    private Integer quantite;
    private Integer montant;
    private Boolean optionMontage;
}

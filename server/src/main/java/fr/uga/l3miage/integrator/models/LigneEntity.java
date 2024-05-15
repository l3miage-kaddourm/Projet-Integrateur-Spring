package fr.uga.l3miage.integrator.models;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LigneEntity {
    @Id
    private String id;

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

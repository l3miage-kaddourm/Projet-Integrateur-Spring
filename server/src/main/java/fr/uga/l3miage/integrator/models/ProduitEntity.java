package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.Encoubrement;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduitEntity {
    @Id
    private String reference;

    private String photo;

    private String titre;

    @Column(length = 1000)
    private String description;

    private boolean optionMontage;

    private double prix;


//    @Column(nullable = true)
//    private Integer tdmTheorique;

    @Enumerated(EnumType.STRING)
    private Encoubrement encoubrement;


    @OneToMany(mappedBy = "produit")
    private Set<LigneEntity> lignesCommandes;

//    @ManyToOne
//    private CatalogueEntity catalogue;
//
    @ManyToMany
    private Set<EntrepotEntity> entrepots;

//    @ManyToMany(mappedBy = "produits")
//    private Set<CommandeEntity> commandes;
}

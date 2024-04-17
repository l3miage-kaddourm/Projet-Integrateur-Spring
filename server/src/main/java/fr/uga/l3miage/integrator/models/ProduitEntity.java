package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.Encoubrement;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
public class ProduitEntity {
    @Id
    private String reference;

    private String photo;

    private String titre;

    private String description;

    private boolean optionMontage;

    private double prix;


    @Column(nullable = true)
    private Integer tdmTheorique;

    @Enumerated(EnumType.STRING)
    private Encoubrement encoubrement;


    @OneToMany(mappedBy = "produit")
    private Set<LigneEntity> lignesCommandes;


    @ManyToOne
    private Catalogue catalogue;

    @ManyToMany
    private Set<EntrepotEntity> entrepots;
}

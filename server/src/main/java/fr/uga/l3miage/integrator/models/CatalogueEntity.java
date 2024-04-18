package fr.uga.l3miage.integrator.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class CatalogueEntity {


    @Id
    private String reference;

    @OneToMany(mappedBy = "catalogue")
    private Set<ProduitEntity> produits;

}

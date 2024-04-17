package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.DataType.Adresse;
import fr.uga.l3miage.integrator.DataType.GeoPosition;

import javax.persistence.*;
import java.util.Set;

@Entity
public class EntrepotEntity {
    @Id
    private String nom;

    private String lettre;

    private String photo;

    @Embedded
    private Adresse adresse;

    @Embedded
    private GeoPosition position;

    @OneToMany(mappedBy = "entrepot")
    private Set<CamionEntity> camions;

    @OneToMany(mappedBy = "entrepot")
    private Set<JourneeEntity> journees;

    @OneToOne(mappedBy = "entrepot")
    private EmployeEntity employe;

    @ManyToMany(mappedBy = "entrepots")
    private Set<ProduitEntity> produits;


}

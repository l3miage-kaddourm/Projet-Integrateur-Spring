package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.DataType.Adresse;
import fr.uga.l3miage.integrator.DataType.GeoPosition;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
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
//
    @OneToMany(mappedBy = "entrepot")
    private Set< EmployeEntity> employes;

    @ManyToMany(mappedBy = "entrepots")
    private Set<ProduitEntity> produits;

}

package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.DataType.Adresse;
import fr.uga.l3miage.integrator.DataType.GeoPosition;
import fr.uga.l3miage.integrator.enums.EtatsDeClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    private String email;
    private String prenom;
    private String nom;
    private Double montantTotal;
    private Adresse adresse;
    private GeoPosition position;
    private EtatsDeClient etat;

    @OneToMany(mappedBy = "client")
    private Set<CommandeEntity> commandes;
}

package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.EtatsDeClient;
import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

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
}

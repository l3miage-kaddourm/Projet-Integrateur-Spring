package fr.uga.l3miage.integrator.requests;

import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommandeCreateRequest {
    private String reference;
    private EtatsDeCommande etat;
    private LocalDateTime dateDeCreation;
    private Integer note;
    private String commentaire;
    private double montant;
    private Integer tddTheorique;
    private Integer tdmTheorique;
    private LocalDateTime dateDeLivraisonEffective;
    private Integer dureeDeLivraison;
    private ClientCreateRequest client; // Ajoutez ceci
}

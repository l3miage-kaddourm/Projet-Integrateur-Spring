package fr.uga.l3miage.integrator.requests;

import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TourneeFromJourneeCreateRequest {
    private String reference;

    private EtatsDeTournee etat;

    private String lettre;

    private Double montant;

    private Double distanceAparcourir;

    private CamionFromJourneeRequest camion;


    private Set<LivraisonCreateRequest> livraisons;


    private Set<EmployeCreateRequest> employes; // Added this line



}

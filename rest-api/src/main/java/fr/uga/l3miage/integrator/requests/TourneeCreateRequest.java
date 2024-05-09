package fr.uga.l3miage.integrator.requests;


import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourneeCreateRequest {
    private String reference;
    private EtatsDeTournee etat;
    private String lettre;
    private Double montant;
    private Integer tdmTheorique;
    private Integer tdmEffective;
    private Double distanceAparcourir;
    private Double distanceDeRetour;
    private JourneeResponseDTO journee;
}

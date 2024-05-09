package fr.uga.l3miage.integrator.responses;


import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "TourneeResponseDTO", description = "Tournee response")
public class TourneeResponseDTO {
    @Schema(description = "Tournee reference")
    private String reference;

    @Schema(description = "Tournee state")
    private EtatsDeTournee etat;

    @Schema(description = "Tournee letter")
    private String lettre;

    @Schema(description = "Tournee amount")
    private Double montant;


    @Schema(description = "Tournee theorical time")
    private Integer tdmTheorique;

    @Schema(description = "Tournee effective time")
    private Integer tdmEffective;

    @Schema(description = "Tournee distance to travel")
    private Double distanceAparcourir;

    @Schema(description = "Tournee return distance")
    private Double distanceDeRetour;

    @Schema(description = "Tournee day")
    private JourneeResponseDTO journee;
}

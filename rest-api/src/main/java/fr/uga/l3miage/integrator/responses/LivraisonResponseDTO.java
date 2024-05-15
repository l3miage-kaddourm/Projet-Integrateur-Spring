package fr.uga.l3miage.integrator.responses;


import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "LivraisonResponseDTO", description = "Livraison response")
public class LivraisonResponseDTO {

    @Schema(description = "Livraison reference", example = "L123456")
    private String reference;

    @Schema(description = "Livraison state", example = "enCours")
    private EtatsDeLivraison etat;

    @Schema(description = "Montant de chaque ligne", example = "100.0")
    private Double montant;

    @Schema(description = "Livraison distance à parcourir", example = "100.0")
    private Double distanceAparcourir;

    @Schema(description = "Livraison distance de retour", example = "100.0")
    private Integer tdpAlAller;

    @Schema(description = "Livraison distance de retour", example = "100.0")
    private Integer tdpTheorique;

    @Schema(description = "Livraison distance de retour", example = "100.0")
    private Integer tdmTheorique;

    @Schema(description = "heure de livraison", example = "12:00:00")
    private Time heureDeLivraison;

    @Schema(description = "heure de livraison effective", example = "12:00:00")
    private Time heureDeLivraisonEffective;

    @Schema(description = "temps de montage", example = "10")
    private Integer tdmEffectif;


}

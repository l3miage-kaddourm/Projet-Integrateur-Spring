package fr.uga.l3miage.integrator.responses;


import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "LivraisonResponseDTO", description = "Livraison response")
public class LivraisonResponseDTO {

    private String reference;

    private EtatsDeLivraison etat;

    private Double montant;

    private Double distanceAparcourir;

    private Integer tdpAlAller;

    private Integer tdpTheorique;

    private Integer tdmTheorique;

    private Time heureDeLivraison;

    private LocalDateTime heureDeLivraisonEffective;

    private Integer tdmEffectif;




}

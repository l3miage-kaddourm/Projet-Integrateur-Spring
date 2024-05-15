package fr.uga.l3miage.integrator.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import lombok.*;

import java.sql.Time;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonCreateRequest {
    private String reference;
    private EtatsDeLivraison etat;
    private Double montant;
    private Double distanceAparcourir;
    private Integer tdpAlAller;
    private Integer tdpTheorique;
    private Integer tdmTheorique;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time heureDeLivraison;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time heureDeLivraisonEffective;
    private Integer tdmEffectif;
    private Set<CommandeCreateRequest> commandes;
}

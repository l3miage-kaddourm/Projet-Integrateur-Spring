package fr.uga.l3miage.integrator.responses;


import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "CommandeResponseDTO", description = "Commande response")
public class CommandeResponseDTO {

        @Schema(description = "id de la commande", example = "1")
        private String reference;

        @Schema(description = "etat de la commande", example = "EN_COURS")
        private EtatsDeCommande etat;

        @Schema(description = "date de creation de la commande", example = "2021-05-12T12:00:00")
        private LocalDateTime dateDeCreation ;


        @Schema(description = "note de la commande", example = "5")
        private Integer note;

        @Schema(description = "commentaire de la commande", example = "bonne commande")
        private String commentaire;

        @Schema(description = "montant de la commande", example = "100.0")
        private double montant;


        @Schema(description = "duree de preparation de la commande", example = "10")
        private Integer tddTheorique;


        @Schema(description = "duree de preparation de la commande", example = "10")
        private Integer tdmTheorique;

        @Schema(description = "date de livraison effective de la commande", example = "2021-05-12T12:00:00")
        private LocalDateTime dateDeLivraisonEffective;


        @Schema(description = "duree de livraison de la commande", example = "10")
        private Integer dureeDeLivraison;

        @Schema(description = "livraison de la commande")
        private LivraisonResponseDTO livraison;

        @Schema(description = "client de la commande")
        private ClientResponseDTO client;

        private Set<LigneResponseDTO> lignesProduits;



}

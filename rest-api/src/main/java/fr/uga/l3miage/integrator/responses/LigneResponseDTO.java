package fr.uga.l3miage.integrator.responses;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "LigneResponseDTO", description = "Ligne response")
public class LigneResponseDTO {

    @Schema(description = "id de la ligne", example = "1")
    private String id;

    @Schema(description = "quantite de la ligne", example = "2")
    private Integer quantite;

    @Schema(description = "montant de la ligne", example = "100")
    private Integer montant;

    @Schema(description = "option de montage de la ligne", example = "true")
    private Boolean optionMontage;


    @Schema(description = "produit de la ligne")
    private ProduitResponseDTO produit;
}

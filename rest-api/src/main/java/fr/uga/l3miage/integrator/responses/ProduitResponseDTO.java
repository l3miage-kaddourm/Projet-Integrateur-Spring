package fr.uga.l3miage.integrator.responses;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "ProduitResponseDTO", description = "DTO for Produit")
public class ProduitResponseDTO {

    @Schema(description = "Reference of the produit", example = "123456")
    private String reference;

    @Schema(description = "Photo of the produit", example = "photo.jpg")
    private String photo;


    @Schema(description = "Titre of the produit", example = "Titre")
    private String titre;


    @Schema(description = "Description of the produit", example = "Description")
    private String description;


    @Schema(description = "Prix of the produit", example = "12.5")
    private double prix;

    private boolean optionMontage;
}

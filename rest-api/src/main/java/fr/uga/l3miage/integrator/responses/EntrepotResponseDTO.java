package fr.uga.l3miage.integrator.responses;

import fr.uga.l3miage.integrator.DataType.Adresse;
import fr.uga.l3miage.integrator.DataType.GeoPosition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Embedded;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "EntrepotReponseDTO", description = "Entrepot response")
public class  EntrepotResponseDTO {
    @Schema(description = "Entrepot name", example = "Grenis")
    private String nom;

    @Schema(description = "Entrepot letter", example = "G")
    private String lettre;

    @Schema(description = "Entrepot photo", example = "photo.jpg")
    private String photo;

    @Embedded
    @Schema(description = "Entrepot address", example = "1 rue des fleurs")
    private Adresse adresse;

    @Embedded
    @Schema(description = "Entrepot position", example = "45.0, 5.0")
    private GeoPosition position;
}



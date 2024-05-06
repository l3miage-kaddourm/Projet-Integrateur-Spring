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
public class EntrepotResponseDTO {
    @Schema(description = "Entrepot name")
    private String nom;

    @Schema(description = "Entrepot letter")
    private String lettre;

    @Schema(description = "Entrepot photo")
    private String photo;

    @Embedded
    @Schema(description = "Entrepot address")
    private Adresse adresse;

    @Embedded
    @Schema(description = "Entrepot position")
    private GeoPosition position;
}

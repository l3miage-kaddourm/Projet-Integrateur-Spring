package fr.uga.l3miage.integrator.responses;


import fr.uga.l3miage.integrator.DataType.GeoPosition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "CamionResponseDTO", description = "DTO for Camion")
public class CamionResponseDTO {

    @Schema(description = "Immatriculation of the camion", example = "AA-123-BB")
    private String immatriculation;

    @Schema(description = "Position of the camion", example = "45.0, 5.0")
    private GeoPosition position;

    @Schema(description = "Entrepot of the camion")
    private EntrepotResponseDTO entrepot;


}

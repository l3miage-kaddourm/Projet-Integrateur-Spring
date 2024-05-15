package fr.uga.l3miage.integrator.responses;

import fr.uga.l3miage.integrator.DataType.Adresse;
import fr.uga.l3miage.integrator.DataType.GeoPosition;
import fr.uga.l3miage.integrator.enums.EtatsDeClient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "ClientResponseDTO", description = "Client response")
public class ClientResponseDTO {

    @Schema(description = "Client reference", example = "abababa@ahahah")
    private String email;

    @Schema(description = "Client first name", example = "Jean")
    private String prenom;

    @Schema(description = "Client last name", example = "Doe")
    private String nom;

    @Schema(description = "Global price", example = "200.0")
    private Double montantTotal;

    @Schema(description = "Client address", example = "1 rue des fleurs")
    private Adresse adresse;

    @Schema(description = "Client position", example = "45.0, 5.0")
    private GeoPosition position;

    @Schema(description = "Client state", example = "actif")
    private EtatsDeClient etat;


}

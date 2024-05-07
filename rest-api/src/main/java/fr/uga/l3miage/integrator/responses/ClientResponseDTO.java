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

    private String email;


    private String prenom;
    private String nom;
    private Double montantTotal;
    private Adresse adresse;
    private GeoPosition position;
    private EtatsDeClient etat;


}

package fr.uga.l3miage.integrator.requests;


import fr.uga.l3miage.integrator.DataType.GeoPosition;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CamionFromJourneeRequest {


    private String immatriculation;

    private GeoPosition position;

}

package fr.uga.l3miage.integrator.CsvStrategies;

import com.opencsv.bean.CsvBindByName;
import fr.uga.l3miage.integrator.DataType.Adresse;
import fr.uga.l3miage.integrator.DataType.GeoPosition;
import fr.uga.l3miage.integrator.enums.EtatsDeClient;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientStrategie {

    @CsvBindByName(column = "email")
    private String email;

    @CsvBindByName(column = "prénom")
    private String prenom;

    @CsvBindByName(column = "nom")
    private String nom;

    @CsvBindByName(column = "montant Total")
    private Double montantTotal;

    @CsvBindByName(column = "adresse")
    private String adresse;

    @CsvBindByName(column = "code postal")
    private String codePostal;

    @CsvBindByName(column = "ville")
    private String ville;

    @CsvBindByName(column = "/latitude")
    private Double latitude;

    @CsvBindByName(column = "/longitude")
    private Double longitude;

    @CsvBindByName(column = "etat")
    private EtatsDeClient etat;
}

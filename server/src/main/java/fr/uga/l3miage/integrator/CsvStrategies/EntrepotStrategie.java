package fr.uga.l3miage.integrator.CsvStrategies;


import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrepotStrategie {

    @CsvBindByName(column = "nom")
    private String nom;

    @CsvBindByName(column = "lettre")
    private String lettre;

    @CsvBindByName(column = "photo")
    private String photo;

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


}

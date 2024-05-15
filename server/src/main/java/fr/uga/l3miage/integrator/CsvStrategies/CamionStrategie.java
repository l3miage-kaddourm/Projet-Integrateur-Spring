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
public class CamionStrategie {

    @CsvBindByName(column = "immatriculation")
    private String immatriculation;

    @CsvBindByName(column = "latitude")
    private double laltitude;

    @CsvBindByName(column = "longitude")
    private double longitude;

    @CsvBindByName(column = "entrepôt")
    private String entrepot;
}

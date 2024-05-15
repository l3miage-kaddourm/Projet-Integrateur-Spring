package fr.uga.l3miage.integrator.CsvStrategies;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitStrategie {


    @CsvBindByName(column = "référence")
    private String reference;

    @CsvBindByName(column = "photo")
    private String photo;

    @CsvBindByName(column = "titre")
    private String titre;

    @CsvBindByName(column = "description")
    private String description;

    @CsvCustomBindByName(column = "prix", converter = PriceConverter.class)
    private double prix;

    @CsvBindByName(column = "option montage")
    private boolean optionMontage;


}

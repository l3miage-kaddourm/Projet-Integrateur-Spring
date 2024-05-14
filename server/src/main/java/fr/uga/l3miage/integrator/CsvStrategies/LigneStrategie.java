package fr.uga.l3miage.integrator.CsvStrategies;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LigneStrategie {

    @CsvBindByName(column = "référence")
    private String id;

    @CsvBindByName(column = "commande")
    private String referenceCommande;

    @CsvBindByName(column = "produit")
    private String referenceProduit;

    @CsvBindByName(column = "quantité")
    private Integer quantite;


    @CsvBindByName(column = "option montage")
    private Boolean optionMontage;
}

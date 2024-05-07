package fr.uga.l3miage.integrator.CsvStrategies;


import com.opencsv.bean.CsvBindByName;
import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.enums.Note;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommandeStrategie {

    @CsvBindByName(column = "référence")
    private String reference;

    @CsvBindByName(column = "état")
    private EtatsDeCommande etat;

    @CsvBindByName(column = "date de création")
    private String dateDeCreation;

    @CsvBindByName(column = "note")
    private Integer note;

    @CsvBindByName(column = "commentaire")
    private String commentaire;


    @CsvBindByName(column = "client")
    private String client;

}

package fr.uga.l3miage.integrator.CsvStrategies;


import com.opencsv.bean.CsvBindByName;
import fr.uga.l3miage.integrator.enums.Emploi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeStrategie {
    @CsvBindByName(column = "trigramme")
    private String trigramme;

    @CsvBindByName(column = "email")
    private String email;

    @CsvBindByName(column = "prénom")
    private String prenom;

    @CsvBindByName(column = "nom")
    private String nom;

    @CsvBindByName(column = "photo")
    private String photo;

    @CsvBindByName(column = "téléphone")
    private String telephone;

    @CsvBindByName(column = "emploi")
    private Emploi emploi;

    @CsvBindByName(column = "entrepôt")
    private String entrepot;
}

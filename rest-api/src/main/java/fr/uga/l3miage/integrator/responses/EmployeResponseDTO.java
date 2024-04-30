package fr.uga.l3miage.integrator.responses;


import com.opencsv.bean.CsvBindByName;
import fr.uga.l3miage.integrator.enums.Emploi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "EmployeResponseDTO", description = "Employe response")
public class EmployeResponseDTO {
    @Schema(description = "Employe id")
    @CsvBindByName(column = "trigramme")
    private String trigramme;
    @Schema(description = "Employe name")
    @CsvBindByName(column = "email")
    private String email;
    @Schema(description = "Employe first name")
    @CsvBindByName(column = "prénom")
    private String prenom;
    @Schema(description = "Employe last name")
    @CsvBindByName(column = "nom")
    private String nom;
    @Schema(description = "Employe photo")
    @CsvBindByName(column = "photo")
    private String photo;
    @Schema(description = "Employe phone number")
    @CsvBindByName(column = "téléphone")
    private String telephone;
    @Schema(description = "Employe job")
    @CsvBindByName(column = "emploi")
    private Emploi emploi;
}

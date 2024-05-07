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
    private String trigramme;

    @Schema(description = "Employe name")
    private String email;

    @Schema(description = "Employe first name")
    private String prenom;

    @Schema(description = "Employe last name")
    private String nom;

    @Schema(description = "Employe photo")
    private String photo;

    @Schema(description = "Employe phone number")
    private String telephone;

    @Schema(description = "Employe job")
    private Emploi emploi;


    @Schema(description = "Employe entrepot")
    private String entrepot;

}

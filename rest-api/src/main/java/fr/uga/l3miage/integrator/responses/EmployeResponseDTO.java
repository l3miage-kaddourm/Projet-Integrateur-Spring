package fr.uga.l3miage.integrator.responses;


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
    @Schema(description = "Employe id", example = "ABC")
    private String trigramme;


    @Schema(description = "Employe first name", example = "John")
    private String prenom;


    @Schema(description = "Employe last name, example = 'Doe'")
    private String nom;

    @Schema(description = "Employe photo", example = "photo.jpg")
    private String photo;


    @Schema(description = "Employe phone number", example = "0123456789")
    private String telephone;

    @Schema(description = "Employe job", example = "planificateur")
    private Emploi emploi;

    @Schema(description = "Employe email", example = "ahahahahha@hdhdhhdhd")
    private String email;


    @Schema(description = "Employe entrepot", example = "A")
    private String entrepot;


    private TourneeResponseDTO tournee;


}

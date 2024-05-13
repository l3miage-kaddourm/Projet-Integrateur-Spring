package fr.uga.l3miage.integrator.responses;


import com.fasterxml.jackson.annotation.JsonFormat;
import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "JourneeResponseDTO", description = "Journee response")

public class JourneeResponseDTO {

    @Schema(description = "Journee reference", example = "JOURNEE-1")
    private String reference;

    @Schema(description = "Journee etat", example = "EN_COURS")
    private EtatsDeJournee etat;

    @Schema(description = "Journee date", example = "2021-01-01")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;


    @Schema(description = "Journee distanceAParcourir", example = "100.0")
    private Double distanceAParcourir;

    @Schema(description = "Journee distanceDeRetour", example = "100.0")
    private Double montant;


    @Schema(description = "Journee tournees", example = "TourneeEntity")
    private Set<TourneeResponseDTO> tournees;


    @Schema(description = "Journee entrepot", example = "EntrepotEntity")
    private EntrepotResponseDTO entrepot;


}

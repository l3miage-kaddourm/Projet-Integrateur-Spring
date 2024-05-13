package fr.uga.l3miage.integrator.requests;


import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandeCreationRequest {
    private String reference;
    private EtatsDeCommande etat;
    private LocalDateTime dateDeCreation;
    private Integer note;
    private String commentaire;
}

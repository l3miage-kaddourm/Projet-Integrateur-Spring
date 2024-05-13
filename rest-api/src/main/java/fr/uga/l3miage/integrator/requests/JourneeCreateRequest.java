package fr.uga.l3miage.integrator.requests;


import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class JourneeCreateRequest {

    private String reference;

    private EtatsDeJournee etat;

    private Date date;

    private Double distanceAParcourir;

    private Double montant;

    private Set<TourneeFromJourneeCreateRequest> tournees;

    private String entrepotName ;
}

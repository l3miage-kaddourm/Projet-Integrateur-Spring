package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JourneeEntity {
    @Id
    private String reference;
    private EtatsDeJournee etat;
    private Date date;
    private Double distanceAParcourir;
    private Double montant;
    private Integer tdmTheorique;
}

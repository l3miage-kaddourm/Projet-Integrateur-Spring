package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class JourneeEntity {
    @Id
    private String reference;
    @Enumerated(EnumType.STRING)
    private EtatsDeJournee etat;

    private Date date;

    private Double distanceAParcourir;

    private Double montant;

    private Integer tdmTheorique;

    @OneToMany(mappedBy = "journee")
    private Set<TourneeEntity> tournees;

//    @ManyToOne
//    private EntrepotEntity entrepot;


}

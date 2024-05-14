package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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

    @OneToMany(mappedBy = "journee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TourneeEntity> tournees = new HashSet<>();

    @ManyToOne
    private EntrepotEntity entrepot;

    public void addTournee(TourneeEntity tournee) {
        tournees.add(tournee);
        tournee.setJournee(this);
    }

    public void removeTournee(TourneeEntity tournee) {
        tournees.remove(tournee);
        tournee.setJournee(null);
    }
}

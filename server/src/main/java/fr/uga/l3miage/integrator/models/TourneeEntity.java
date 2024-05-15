package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TourneeEntity {
    @Id
    private String reference;

    @Enumerated(EnumType.STRING)
    private EtatsDeTournee etat;

    private String lettre;

    private Double montant;

    private Double distanceAparcourir;

    @ManyToOne
    private CamionEntity camion;

    @OneToMany(mappedBy = "tournee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LivraisonEntity> livraisons = new HashSet<>();

    @ManyToOne
    private JourneeEntity journee;

    @OneToMany(mappedBy = "tournee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeEntity> employes = new HashSet<>();

    public void addLivraison(LivraisonEntity livraison) {
        livraisons.add(livraison);
        livraison.setTournee(this);
    }

    public void removeLivraison(LivraisonEntity livraison) {
        livraisons.remove(livraison);
        livraison.setTournee(null);
    }

    public void addEmploye(EmployeEntity employe) {
        employes.add(employe);
        employe.setTournee(this);
    }
}

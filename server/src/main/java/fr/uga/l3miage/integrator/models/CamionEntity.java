package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.DataType.GeoPosition;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CamionEntity {
    @Id
    private String immatriculation;

    private GeoPosition position;


    @ManyToOne
    private EntrepotEntity entrepot;

//    @OneToMany(mappedBy = "camion")
//    private Set<TourneeEntity> tournees;

}

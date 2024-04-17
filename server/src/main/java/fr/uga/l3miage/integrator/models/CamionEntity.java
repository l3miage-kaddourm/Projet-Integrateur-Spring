package fr.uga.l3miage.integrator.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CamionEntity {
    @Id
    private String immatriculation;
    private GeoPosition position;

}

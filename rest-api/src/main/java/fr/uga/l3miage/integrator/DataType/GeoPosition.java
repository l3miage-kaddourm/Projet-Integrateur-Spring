package fr.uga.l3miage.integrator.DataType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
public class GeoPosition {
    private Double laltitude;
    private Double longitude;
}

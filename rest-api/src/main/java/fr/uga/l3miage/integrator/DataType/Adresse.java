package fr.uga.l3miage.integrator.DataType;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
public class Adresse {
    private String adresse;
    private String codePostal;
    private String ville;
}

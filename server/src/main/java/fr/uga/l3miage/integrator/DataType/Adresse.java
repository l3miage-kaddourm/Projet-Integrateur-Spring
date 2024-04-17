package fr.uga.l3miage.integrator.DataType;


import javax.persistence.Embeddable;

@Embeddable

public class Adresse {
    private String adresse;
    private String codePostal;
    private String ville;
}

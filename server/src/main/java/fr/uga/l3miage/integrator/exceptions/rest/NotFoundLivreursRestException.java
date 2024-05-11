package fr.uga.l3miage.integrator.exceptions.rest;


import lombok.Getter;

@Getter

public class NotFoundLivreursRestException extends RuntimeException{

    private final Type type;

    public NotFoundLivreursRestException(String message, Type type){
        super(message);
        this.type = type;
    }

    public enum Type{
        NOTFOUND,
        UNKNOWN
    }
}

package fr.uga.l3miage.integrator.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotFoundLivreursError {
    private String message;
    private TypeError typeError;

    public enum TypeError {
        NOTFOUND,
        UNKNOWN
    }
}
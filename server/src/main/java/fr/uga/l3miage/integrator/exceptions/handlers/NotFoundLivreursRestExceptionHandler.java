package fr.uga.l3miage.integrator.exceptions.handlers;


import fr.uga.l3miage.integrator.exceptions.NotFoundLivreursError;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundLivreursRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class NotFoundLivreursRestExceptionHandler {
    @ExceptionHandler(NotFoundLivreursRestException.class)
    ResponseEntity<NotFoundLivreursError> handle(HttpServletRequest httpServletRequest, NotFoundLivreursRestException exception) {
        NotFoundLivreursError error = NotFoundLivreursError.builder()
                .message(exception.getMessage())
                .typeError(exception.getType() == NotFoundLivreursRestException.Type.NOTFOUND ? NotFoundLivreursError.TypeError.NOTFOUND : NotFoundLivreursError.TypeError.UNKNOWN)
                .build();
        return ResponseEntity.status(404).body(error);
    }
}

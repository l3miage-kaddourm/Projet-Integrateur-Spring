package fr.uga.l3miage.integrator.exceptions.handlers;


import fr.uga.l3miage.integrator.exceptions.NotFoundCamionError;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundCamionRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundCamionRestExceptionHandler {

    @ExceptionHandler(NotFoundCamionRestException.class)
    ResponseEntity<NotFoundCamionError> handle(NotFoundCamionRestException exception) {
        NotFoundCamionError error = NotFoundCamionError.builder()
                .message(exception.getMessage())
                .typeError(exception.getType() == NotFoundCamionRestException.Type.NOTFOUND ? NotFoundCamionError.TypeError.NOTFOUND : NotFoundCamionError.TypeError.UNKNOWN)
                .build();
        return ResponseEntity.status(490).body(error);
    }
}

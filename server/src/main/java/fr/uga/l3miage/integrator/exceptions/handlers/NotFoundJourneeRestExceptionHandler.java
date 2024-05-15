package fr.uga.l3miage.integrator.exceptions.handlers;


import fr.uga.l3miage.integrator.exceptions.NotFoundJourneeError;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundJourneeRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundJourneeRestExceptionHandler  {

    @ExceptionHandler(NotFoundJourneeRestException.class)
    ResponseEntity<NotFoundJourneeError> handle (NotFoundJourneeRestException exception){
        NotFoundJourneeError error = NotFoundJourneeError.builder()
                .message(exception.getMessage())
                .typeError(exception.getType() == NotFoundJourneeRestException.Type.NOTFOUND ? NotFoundJourneeError.TypeError.NOTFOUND : NotFoundJourneeError.TypeError.UNKNOWN)
                .build();
        return ResponseEntity.status(503).body(error);
    }
}

package fr.uga.l3miage.integrator.exceptions.handlers;


import fr.uga.l3miage.integrator.exceptions.NotFoundLivreursError;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEmployeRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class NotFoundEmployeRestExceptionHandler {
    @ExceptionHandler(NotFoundEmployeRestException.class)
    ResponseEntity<NotFoundLivreursError> handle(HttpServletRequest httpServletRequest, NotFoundEmployeRestException exception) {
        NotFoundLivreursError error = NotFoundLivreursError.builder()
                .message(exception.getMessage())
                .typeError(exception.getType() == NotFoundEmployeRestException.Type.NOTFOUND ? NotFoundLivreursError.TypeError.NOTFOUND : NotFoundLivreursError.TypeError.UNKNOWN)
                .build();
        return ResponseEntity.status(404).body(error);
    }
}

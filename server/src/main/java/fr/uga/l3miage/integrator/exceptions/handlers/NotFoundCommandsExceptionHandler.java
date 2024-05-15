package fr.uga.l3miage.integrator.exceptions.handlers;


import fr.uga.l3miage.integrator.exceptions.NotFoundCommandsError;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundCommandsRestException;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundCommandsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class NotFoundCommandsExceptionHandler {

    @ExceptionHandler(NotFoundCommandsRestException.class)
    ResponseEntity<NotFoundCommandsError> handle(HttpServletRequest httpServletRequest, NotFoundCommandsRestException exception) {
        NotFoundCommandsError error = NotFoundCommandsError.builder()
                .message(exception.getMessage())
                .typeError(exception.getType() == NotFoundCommandsRestException.Type.NOTFOUND ? NotFoundCommandsError.TypeError.NOTFOUND : NotFoundCommandsError.TypeError.UNKNOWN)
                .build();
        return ResponseEntity.status(408).body(error);
    }
}

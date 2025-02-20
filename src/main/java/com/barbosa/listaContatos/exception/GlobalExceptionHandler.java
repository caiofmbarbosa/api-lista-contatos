package com.barbosa.listaContatos.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidatorException(MethodArgumentNotValidException exception) {
        log.error("Validator error:", exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid fields, try again!");

    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<String> handleContactNotFoundException(ContactNotFoundException exception) {
        log.error("Contact not found:", exception);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());

    }

    @ExceptionHandler(NoContactRegisteredException.class)
    public ResponseEntity<String> handleNoContactRegisteredException(NoContactRegisteredException exception) {
        log.error("No contact registered:", exception);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());

    }

    public ResponseEntity<String> handleRepositoryException(RepositoryException exception) {
        log.error("Repository broked:", exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralErrors(Exception exception) {
        log.error("Internal server error:", exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error, contact an administrator!");

    }

}

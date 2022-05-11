package com.conference.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> EntityNotFound(CustomException ex) {
        EntityErrorResponse postErrorRes = new EntityErrorResponse(
                ex.getStatus(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(ex.getStatus()).body(postErrorRes);
    }
}

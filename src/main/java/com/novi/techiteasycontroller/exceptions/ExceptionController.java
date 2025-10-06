package com.novi.techiteasycontroller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        // change to ResponseEntity.status (without using NEW)
    }

    @ExceptionHandler(value = NameTooLongException.class)
    public ResponseEntity<Object> handleNameTooLong(NameTooLongException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException exception){
            Map<String, String> errors = new HashMap<>();

            exception.getBindingResult().getFieldErrors().forEach((e) -> errors.put(e.getField(), e.getDefaultMessage()));

            return ResponseEntity.badRequest().body(errors);
    }
}

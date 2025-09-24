package com.novi.techiteasycontroller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NameTooLongException.class)
    public ResponseEntity<Object> handleNameTooLong(NameTooLongException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // I wrote the following as one of the points in the bonus, but to be honest it was already handled neatly
    // in the methode above, which catches any ID that's not in the list. So according to "BONUS BONUS",
    // it's irrelevant.

//    @ExceptionHandler(value = IndexOutOfBoundsException.class)
//    public ResponseEntity<Object> handleIndexOutOfBound(IndexOutOfBoundsException exception){
//        return new ResponseEntity<>("Index out of bound!", HttpStatus.NOT_FOUND);
//    }

}

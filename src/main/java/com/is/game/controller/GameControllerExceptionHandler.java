/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.game.controller;

import com.is.game.service.GuessWithDuplicatesException;
import com.is.game.service.IncorrectGuessException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author ibby4
 */
@ControllerAdvice
@RestController
public class GameControllerExceptionHandler extends ResponseEntityExceptionHandler {
 
    private static final String CONSTRAINT_MESSAGE = "Invalid GameId Entered/ Game not found";

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Error> handleSqlException(
            SQLIntegrityConstraintViolationException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IncorrectGuessException.class)
    public final ResponseEntity<Error> handleSqlException(
            IncorrectGuessException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage("Guess can only be 4 digit number. Try again!");
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(GuessWithDuplicatesException.class)
    public final ResponseEntity<Error> handleSqlException(
            GuessWithDuplicatesException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage("Answer has no duplicate digits therefore, Guess canot contain duplicate digits. Try again!");
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
  
}

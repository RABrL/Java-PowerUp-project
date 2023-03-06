package com.example.restaurant_plaza.infrastructure.exceptionhandler;

import com.example.restaurant_plaza.infrastructure.exception.NoDataFoundException;
import com.example.restaurant_plaza.infrastructure.exception.UserAlreadyExistException;
import com.example.restaurant_plaza.infrastructure.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(NoDataFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE,ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE,ExceptionResponse.USER_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistException(UserAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE,ExceptionResponse.USER_ALREADY_EXIST.getMessage()));
    }
}

package com.example.restaurant_plaza.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the request petition"),
    USER_NOT_FOUND("User not found in the database"),
    USER_ALREADY_EXIST("User already exist in the database"),
    EMAIL_OR_PASSWORD_INCORRECT("Email or password incorrect");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}


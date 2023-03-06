package com.example.restaurant_plaza.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the request petition"),
    USER_NOT_FOUND("User not exist in the database"),
    USER_ALREADY_EXIST("User already exist in the database");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}


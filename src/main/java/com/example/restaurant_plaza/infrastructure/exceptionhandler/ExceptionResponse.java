package com.example.restaurant_plaza.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the request petition");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}


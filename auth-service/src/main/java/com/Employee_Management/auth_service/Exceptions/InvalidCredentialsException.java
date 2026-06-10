package com.Employee_Management.auth_service.Exceptions;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(
            String message) {

        super(message);
    }

}

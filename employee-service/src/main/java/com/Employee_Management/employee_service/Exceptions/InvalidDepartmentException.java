package com.Employee_Management.employee_service.Exceptions;

public class InvalidDepartmentException extends RuntimeException{

    public InvalidDepartmentException(String message) {
        super(message);
    }
}

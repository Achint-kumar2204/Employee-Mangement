package com.Employee_Management.employee_service.Exceptions;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

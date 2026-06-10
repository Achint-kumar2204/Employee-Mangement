package com.Employee_Management.department_service.Exception;

public class DepartmentNotFoundException extends RuntimeException{

    public DepartmentNotFoundException(String message) {
        super(message);
    }

}

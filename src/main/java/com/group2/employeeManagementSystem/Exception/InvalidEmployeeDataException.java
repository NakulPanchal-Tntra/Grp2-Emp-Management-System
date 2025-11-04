package com.group2.employeeManagementSystem.Exception;

public class InvalidEmployeeDataException extends RuntimeException {
    public InvalidEmployeeDataException(String message) {
        super(message);
    }

    public InvalidEmployeeDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
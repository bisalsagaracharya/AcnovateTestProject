package com.acnovate.project.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String noSupervisorsFoundForEmployee, String employeeName) {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}

package com.skillsup.patterns.exeption;

public class UnauthorizedAccessException extends Exception {

    public UnauthorizedAccessException(String errorMessage) {
        super(errorMessage);
    }
}

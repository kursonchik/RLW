package com.academy.exception;

/**
 * @author : Volha Salash
 */
public class IllegalOperationException extends RuntimeException {

    public IllegalOperationException(String errorMessage) {
        super(errorMessage);
    }
}
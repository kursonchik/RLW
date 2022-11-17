package com.academy.exception;

/**
 * @author : Volha Salash
 */
public class TrainsNotFoundException extends RuntimeException {

    public TrainsNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
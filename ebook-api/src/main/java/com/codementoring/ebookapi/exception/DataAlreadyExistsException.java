package com.codementoring.ebookapi.exception;

public class DataAlreadyExistsException extends RuntimeException{

    public DataAlreadyExistsException(String message){
        super(message);
    }
}

package com.example.socialsync.exceptions;

public class InvalidPostId extends RuntimeException{
    public InvalidPostId(String message){
        super(message);
    }
}

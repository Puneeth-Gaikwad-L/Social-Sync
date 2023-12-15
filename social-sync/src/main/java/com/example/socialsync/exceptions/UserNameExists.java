package com.example.socialsync.exceptions;

public class UserNameExists extends RuntimeException{
    public UserNameExists(String message){
        super(message);
    }
}

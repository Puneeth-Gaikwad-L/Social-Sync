package com.example.socialsync.exceptions;

public class WrongPassword extends RuntimeException{
    public WrongPassword(String message){
        super(message);
    }
}

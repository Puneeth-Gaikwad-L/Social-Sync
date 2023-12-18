package com.example.socialsync.exceptions;

public class PrivatePost extends RuntimeException{
    public PrivatePost(String message){
        super(message);
    }
}

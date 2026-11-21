package com.datheon.bank;

public class InvalidPINException extends Exception{
    public InvalidPINException(String message){
        super(message);
    }
}

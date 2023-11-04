package com.majidim.easybankv4.easybankv4.exception;

public class ParametreException extends Exception{
    public ParametreException(String message){
        super(String.format("**** %s ****",message));
    }
}

package com.lukerd.balancedarmor.exceptions;

public class InvalidArmorItemException extends IllegalArgumentException{
    public InvalidArmorItemException(String msg){
        super(msg);
    }

    public InvalidArmorItemException(Throwable cause){
        super(cause);
    }

    public  InvalidArmorItemException(String msg, Throwable cause){
        super(msg,cause);
    }
}

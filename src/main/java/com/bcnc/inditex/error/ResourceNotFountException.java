package com.bcnc.inditex.error;

public class ResourceNotFountException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public ResourceNotFountException(String msg) {
        super(msg);
    }
}

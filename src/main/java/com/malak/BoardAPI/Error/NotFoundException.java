package com.malak.BoardAPI.Error;

public class NotFoundException extends Throwable {
    public NotFoundException(String message) {
        super(message);
    }
}

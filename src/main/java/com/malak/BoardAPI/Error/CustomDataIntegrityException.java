package com.malak.BoardAPI.Error;

import org.springframework.dao.DataIntegrityViolationException;

public class CustomDataIntegrityException extends Throwable {
    public CustomDataIntegrityException(String s, DataIntegrityViolationException e) {
    }
}

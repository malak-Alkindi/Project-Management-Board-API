package com.malak.BoardAPI.Error;

import org.springframework.dao.DataAccessException;

public class CustomDataAccessException extends Throwable {
    public CustomDataAccessException(String s, DataAccessException e) {
    }
}

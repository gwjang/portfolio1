package com.portfolio1.exception;

import com.portfolio1.base.BaseException;
import org.springframework.jdbc.UncategorizedSQLException;

public class DataAccessException extends BaseException {

    public DataAccessException(UncategorizedSQLException sqlException) {
        super(sqlException.getSQLException().getMessage());
        setThrowable(sqlException);
        printSQLTrace();
    }

    public void printSQLTrace() {
        throwable.printStackTrace();
    }
}
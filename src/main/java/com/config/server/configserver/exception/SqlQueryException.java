package com.config.server.configserver.exception;

public class SqlQueryException extends RuntimeException{
    public SqlQueryException(String msg)
    {
        super(msg);
    }
}

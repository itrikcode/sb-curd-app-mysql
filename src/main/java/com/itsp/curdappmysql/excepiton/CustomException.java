package com.itsp.curdappmysql.excepiton;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private final String title;
    private final HttpStatus statusCode;

    public CustomException(String message, String title, HttpStatus statusCode) {
        super(message);
        this.title = title;
        this.statusCode = statusCode;
    }

    public String getTitle() {
        return title;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}

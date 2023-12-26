package com.itsp.curdappmysql.excepiton;

public class ApiError {

    private final String title;
    private final String message;
    private final int statusCode;

    public ApiError(String title, String message, int statusCode) {
        this.title = title;
        this.message = message;
        this.statusCode = statusCode;
    }

    // Getters for title, message, and statusCode

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

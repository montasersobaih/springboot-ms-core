package com.core.springboot.model;


import java.io.Serializable;

public class Error<T> implements Serializable {

    private String errorCode;
    private String message;
    private String path;
    private String url;
    private T errorDetails;

    private Error() {
    }

    public Error(String errorCode, String message) {
        this(errorCode, message, "", "");
    }

    public Error(String errorCode, String message, T errorDetails) {
        this(errorCode, message, "", "", errorDetails);
    }

    public Error(String errorCode, String message, String path) {
        this(errorCode, message, path, "");
    }

    public Error(String errorCode, String message, String path, T errorDetails) {
        this(errorCode, message, path, "", errorDetails);
    }

    public Error(String errorCode, String message, String path, String url) {
        this(errorCode, message, path, url, null);
    }

    public Error(String errorCode, String message, String path, String url, T errorDetails) {
        this.errorCode = errorCode;
        this.message = message;
        this.path = path;
        this.url = url;
        this.errorDetails = errorDetails;
    }

    public T getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(T errorDetails) {
        this.errorDetails = errorDetails;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


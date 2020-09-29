package com.mj.core.springboot.exception;


import com.mj.core.springboot.exception.enumeration.ExceptionCategory;
import com.mj.core.springboot.model.Error;

import java.util.ArrayList;
import java.util.List;

public abstract class ServiceException extends RuntimeException {

    private final ExceptionCategory category;
    private final String message;
    private final int statusCode;
    private String url;
    private String source;
    private List<Error> errors;

    public ServiceException(ExceptionCategory category, int statusCode, String message) {
        this(category, statusCode, message, "", "", new ArrayList<>());
    }

    public ServiceException(ExceptionCategory category, int statusCode, String message, List<Error> errors) {
        this(category, statusCode, message, "", "", errors);
    }

    public ServiceException(ExceptionCategory category, int statusCode, String message, String source) {
        this(category, statusCode, message, source, "", new ArrayList<>());
    }

    public ServiceException(ExceptionCategory category, int statusCode, String message, String source, List<Error> errors) {
        this(category, statusCode, message, source, "", errors);
    }

    public ServiceException(ExceptionCategory category, int statusCode, String message, String source, String url) {
        this(category, statusCode, message, source, url, new ArrayList<>());
    }

    public ServiceException(ExceptionCategory category, int statusCode, String message, String source, String url, List<Error> errors) {
        this.category = category;
        this.statusCode = statusCode;
        this.message = message;
        this.errors = errors;
    }

    public ExceptionCategory getCategory() {
        return category;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}

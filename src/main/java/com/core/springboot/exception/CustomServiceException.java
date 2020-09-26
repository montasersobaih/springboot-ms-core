package com.core.springboot.exception;

import com.core.springboot.exception.enumeration.ExceptionCategory;
import com.core.springboot.model.Error;

import java.util.List;

public class CustomServiceException extends ServiceException {

    public CustomServiceException(ExceptionCategory category, int statusCode, String message) {
        super(category, statusCode, message);
    }

    public CustomServiceException(ExceptionCategory category, int statusCode, String message, String source) {
        super(category, statusCode, message, source);
    }

    public CustomServiceException(ExceptionCategory category, int statusCode, String message, List<Error> errors) {
        super(category, statusCode, message, errors);
    }

    public CustomServiceException(ExceptionCategory category, int statusCode, String message, String source, List<Error> errors) {
        super(category, statusCode, message, source, errors);
    }

    public CustomServiceException(ExceptionCategory category, int statusCode, String message, String source, String url, List<Error> errors) {
        super(category, statusCode, message, source, url, errors);
    }

    public CustomServiceException(ExceptionCategory category, int statusCode, String message, String source, String url) {
        super(category, statusCode, message, source, url);
    }
}

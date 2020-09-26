package com.core.springboot.exception;


import com.core.springboot.exception.enumeration.ExceptionCategory;

public class ForbiddenException extends ServiceException {

    public ForbiddenException() {
        super(ExceptionCategory.SECURITY, 403, "Forbidden request");
    }

    public ForbiddenException(String message) {
        super(ExceptionCategory.SECURITY, 403, message);
    }
}

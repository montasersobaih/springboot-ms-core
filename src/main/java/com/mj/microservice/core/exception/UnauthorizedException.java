package com.mj.microservice.core.exception;

import com.mj.microservice.core.exception.enumeration.ExceptionCategory;

public class UnauthorizedException extends ServiceException {

    public UnauthorizedException() {
        super(ExceptionCategory.SECURITY, 401, "Unauthorized request");
    }

    public UnauthorizedException(String message) {
        super(ExceptionCategory.SECURITY, 401, message);
    }
}

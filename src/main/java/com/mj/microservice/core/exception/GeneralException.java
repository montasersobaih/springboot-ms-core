package com.mj.microservice.core.exception;

import com.mj.microservice.core.exception.enumeration.ExceptionCategory;

public class GeneralException extends ServiceException {

    public GeneralException() {
        super(ExceptionCategory.GENERAL, 500, "General error");
    }

    public GeneralException(String message) {
        super(ExceptionCategory.GENERAL, 500, message);
    }
}

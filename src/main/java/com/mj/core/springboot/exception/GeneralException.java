package com.mj.core.springboot.exception;

import com.mj.core.springboot.exception.enumeration.ExceptionCategory;

public class GeneralException extends ServiceException {

    public GeneralException() {
        super(ExceptionCategory.GENERAL, 500, "General error");
    }

    public GeneralException(String message) {
        super(ExceptionCategory.GENERAL, 500, message);
    }
}

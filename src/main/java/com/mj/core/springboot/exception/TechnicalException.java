package com.mj.core.springboot.exception;

import com.mj.core.springboot.exception.enumeration.ExceptionCategory;

public class TechnicalException extends ServiceException {

    public TechnicalException() {
        super(ExceptionCategory.TECHNICAL, 500, "Technical error");
    }

    public TechnicalException(String message) {
        super(ExceptionCategory.TECHNICAL, 500, message);
    }
}

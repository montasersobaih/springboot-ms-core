package com.core.springboot.exception;

import com.core.springboot.exception.enumeration.ExceptionCategory;

public class IntegrationException extends ServiceException {
    public IntegrationException() {
        super(ExceptionCategory.INTEGRATION, 500, "Integration error");
    }

    public IntegrationException(String message) {
        super(ExceptionCategory.INTEGRATION, 500, message);
    }

    public IntegrationException(String message, String source) {
        super(ExceptionCategory.INTEGRATION, 500, message, source);
    }
}

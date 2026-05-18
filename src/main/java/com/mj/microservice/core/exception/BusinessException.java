package com.mj.microservice.core.exception;


import com.mj.microservice.core.exception.enumeration.ExceptionCategory;

public class BusinessException extends ServiceException {

    public BusinessException() {
        super(ExceptionCategory.BUSINESS, 400, "Business error");
    }

    public BusinessException(String message) {
        super(ExceptionCategory.BUSINESS, 400, message);
    }
}

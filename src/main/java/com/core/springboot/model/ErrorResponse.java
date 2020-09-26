package com.core.springboot.model;

import com.core.springboot.exception.ServiceException;
import com.core.springboot.utils.CommonContext;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResponse {

    private String code;

    private String message;

    private String id;

    private String url;

    private List<Error> errors;

    public ErrorResponse(ServiceException exception) {
        this.code = exception.getCategory().getCode();
        this.message = exception.getMessage();
        this.url = exception.getUrl();
        this.id = CommonContext.getRequestInfo().getRequestId();
        this.errors = exception.getErrors();
    }
}

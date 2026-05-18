package com.mj.microservice.core.model;

import com.mj.microservice.core.exception.ServiceException;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class ErrorResponse {

    private String code;

    private String message;

    private String requestId;

    private String url;

    private List<Error> errors;

    private ErrorResponse(String code, String message, String requestId, String url, List<Error> errors) {
        this.code = code;
        this.message = message;
        this.requestId = requestId;
        this.url = url;
        this.errors = errors;
    }

    public static ErrorResponse from(ServiceException exception) {
        return new ErrorResponse(
                exception.getCategory().getCode(),
                exception.getMessage(),
                null, // CommonContext.getRequestInfo().getRequestId(),
                exception.getUrl(),
                exception.getErrors()
        );
    }
}

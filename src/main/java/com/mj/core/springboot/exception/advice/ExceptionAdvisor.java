package com.mj.core.springboot.exception.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mj.core.springboot.exception.BusinessException;
import com.mj.core.springboot.exception.CustomServiceException;
import com.mj.core.springboot.exception.GeneralException;
import com.mj.core.springboot.exception.ServiceException;
import com.mj.core.springboot.exception.enumeration.ExceptionCategory;
import com.mj.core.springboot.model.Error;
import com.mj.core.springboot.model.ErrorResponse;
import com.mj.core.springboot.utils.ExceptionUtils;
import com.mj.core.springboot.utils.MessageResourceBundle;
import io.undertow.util.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.MethodNotSupportedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.el.MethodNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@SuppressWarnings("ConstantConditions")
public abstract class ExceptionAdvisor {

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      general exception
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @Nullable
    @ExceptionHandler({Throwable.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, final Throwable ex) {
        GeneralException exception = new GeneralException("Internal server error");

        String[] messages = exception.getMessage().replaceAll("((\\w.)+\\w)+: ", "").split(",");

        List<Error> list = new ArrayList<>();
        for (String e : messages) {
            list.add(new Error(ExceptionUtils.buildErrorCode(exception, "001"), e));
        }

        exception.setErrors(list);
        exception.setSource(ExceptionUtils.getDefaultProjectName());

        ResponseEntity<ErrorResponse> errorResponseResponseEntity = handleException(request, exception);
        // log full exception
        logStackTrace(ex, errorResponseResponseEntity.getBody().getId());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      method argument type mismatch
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, MethodArgumentTypeMismatchException ex) {
        CustomServiceException exception = new CustomServiceException(ExceptionCategory.GENERAL, 400, "Method argument type mismatch");
        final String MESSAGE_FORMAT = "%s data type mismatch, cannot convert \'%s\' to %s";
        String errorMessage = String.format(MESSAGE_FORMAT, ex.getName(), ex.getValue(), ex.getRequiredType().getName());
        exception.getErrors().add(new Error(ExceptionUtils.buildErrorCode(exception, "002"), errorMessage));
        exception.setSource(ExceptionUtils.getDefaultProjectName());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      bad request
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @Nullable
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, BadRequestException ex) {

        CustomServiceException exception = new CustomServiceException(ExceptionCategory.GENERAL, 400, "Bad request");
        exception.getErrors().add(new Error(ExceptionUtils.buildErrorCode(exception, "003"), ex.getMessage()));
        exception.setSource(ExceptionUtils.getDefaultProjectName());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      invalid format
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @Nullable
    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, InvalidFormatException ex) {

        ServiceException exception = new CustomServiceException(ExceptionCategory.GENERAL, 400, "Invalid format");
        exception.getErrors().add(new Error(ExceptionUtils.buildErrorCode(exception, "004"), ex.getMessage()));
        exception.setSource(ExceptionUtils.getDefaultProjectName());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      Missing servlet request parameter
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @Nullable
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, MissingServletRequestParameterException ex) {

        ServiceException exception = new CustomServiceException(ExceptionCategory.GENERAL, 400, "Missing servlet request parameter");
        exception.getErrors().add(new Error(ExceptionUtils.buildErrorCode(exception, "005"), ex.getMessage()));
        exception.setSource(ExceptionUtils.getDefaultProjectName());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      no handle exception
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @Nullable
    @ExceptionHandler({NoHandlerFoundException.class})
    public final ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, NoHandlerFoundException ex) {

        ServiceException exception = new CustomServiceException(ExceptionCategory.GENERAL, 404, "Resource not found");
        exception.getErrors().add(new Error(ExceptionUtils.buildErrorCode(exception, "006"), ex.getMessage()));
        exception.setSource(ExceptionUtils.getDefaultProjectName());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      Method not suppoted
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @Nullable
    @ExceptionHandler({MethodNotSupportedException.class})
    public final ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, MethodNotSupportedException ex) {

        ServiceException exception = new CustomServiceException(ExceptionCategory.GENERAL, 405, "Method not supported");
        exception.getErrors().add(new Error(ExceptionUtils.buildErrorCode(exception, "007"), ex.getMessage()));
        exception.setSource(ExceptionUtils.getDefaultProjectName());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      http method not suppoted
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @Nullable
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public final ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {

        ServiceException exception = new CustomServiceException(ExceptionCategory.GENERAL, 405, "Method not supported");
        exception.getErrors().add(new Error(ExceptionUtils.buildErrorCode(exception, "008"), ex.getMessage()));
        exception.setSource(ExceptionUtils.getDefaultProjectName());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      Method not found
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @Nullable
    @ExceptionHandler({MethodNotFoundException.class})
    public final ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, MethodNotFoundException ex) {

        ServiceException exception = new CustomServiceException(ExceptionCategory.GENERAL, 404, "Method not found");
        exception.getErrors().add(new Error(ExceptionUtils.buildErrorCode(exception, "009"), ex.getMessage()));
        exception.setSource(ExceptionUtils.getDefaultProjectName());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      web exchange bind exception
     * @return ResponseEntity of object
     */
    @Nullable
    @ExceptionHandler({WebExchangeBindException.class})
    public final ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, WebExchangeBindException ex) {
        ServiceException exception = new CustomServiceException(ExceptionCategory.GENERAL, 400, "Web exchange bind exception");
        exception.getErrors().add(new Error(ExceptionUtils.buildErrorCode(exception, "010"), ex.getMessage()));
        exception.setSource(ExceptionUtils.getDefaultProjectName());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      max upload size exception
     * @return <b>ResponseEntity&lt<span>Object</span>&gt</b> of object
     */
    @Nullable
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public final ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, MaxUploadSizeExceededException ex) {
        ServiceException exception = new CustomServiceException(ExceptionCategory.GENERAL, 400, "Max upload size exceeded");
        exception.getErrors().add(new Error(ExceptionUtils.buildErrorCode(exception, "011"), ex.getMessage()));
        exception.setSource(ExceptionUtils.getDefaultProjectName());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      invalid json format
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @Nullable
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, HttpMessageNotReadableException ex) {

        BusinessException exception = new BusinessException("Invalid json format");
        final String errorMessage = ex.getMessage().replaceAll("\\n", "");
        final String patternString = "(\\[\\\")(((?![\\\"]).)*)(\\\"\\])";

        // compile pattern string to pattern object
        Pattern pattern = Pattern.compile(patternString);

        // match results
        Matcher matcher = pattern.matcher(errorMessage);

        // Check whether the pattern match any occurrence
        if (matcher.find()) {
            // a specific field has invalid data format
            String fieldName = StringUtils.capitalize(matcher.group(2));
            String path = matcher.group(2);
            while (matcher.find()) {
                fieldName = fieldName.concat(StringUtils.capitalize(matcher.group(2)));
                path = path.concat(".").concat(matcher.group(2));
            }
            exception.getErrors().add(ExceptionUtils.buildErrorDetails(fieldName.concat(ex.getClass().getSimpleName()), path, exception));
            exception.setSource(getExceptionSource(fieldName, ex));
        } else {
            // the whole request payload is not in proper format
            exception.getErrors().add(ExceptionUtils.buildErrorDetails(ex.getClass().getSimpleName(), exception));
            exception.setSource(getExceptionSource(ex));
        }
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      validation error
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @Nullable
    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, ValidationException ex) {
        BusinessException exception = new BusinessException("Validation error");
        final String patternString = "(['])(((?![']).)*)(['])";
        // compile pattern string to pattern object
        Pattern pattern = Pattern.compile(patternString);

        // match results
        Matcher matcher = pattern.matcher(ex.getMessage());

        // Check whether the pattern match any occurrence
        if (matcher.find()) {
            String fieldName = matcher.group(2);
            String exceptionName = StringUtils.capitalize(fieldName).concat(ex.getClass().getSimpleName());
            exception.getErrors().add(ExceptionUtils.buildErrorDetails(exceptionName, fieldName, exception));
        }
        exception.setSource(ExceptionUtils.getDefaultProjectName());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      constraint violation error
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @Nullable
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, ConstraintViolationException ex) {
        BusinessException exception = new BusinessException("Constraint violation error");
        List<Error> errorList = ex.getConstraintViolations().stream()
                .map(e -> ExceptionUtils.buildErrorDetails(e.getMessageTemplate(), exception))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Error::getErrorCode))))
                .stream().collect(Collectors.toList());

        exception.setErrors(errorList);
        exception.setSource(ExceptionUtils.getDefaultProjectName());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      servlet request binding error
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @Nullable
    @ExceptionHandler({ServletRequestBindingException.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, ServletRequestBindingException ex) {

        BusinessException exception = new BusinessException("Servlet request binding error");
        final String patternString = "(['])(((?![']).)*)(['])";
        // compile pattern string to pattern object
        Pattern pattern = Pattern.compile(patternString);

        // match results
        Matcher matcher = pattern.matcher(ex.getMessage());

        // Check whether the pattern match any occurrence
        if (matcher.find()) {
            String fieldName = matcher.group(2);
            String exceptionName = StringUtils.capitalize(fieldName).concat(ex.getClass().getSimpleName());
            exception.getErrors().add(ExceptionUtils.buildErrorDetails(exceptionName, fieldName, exception));
        }
        exception.setSource(ExceptionUtils.getDefaultProjectName());
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      method argument not valid
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        BusinessException exception = new BusinessException("Method argument not valid");
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        List<Error> errorsList = allErrors.stream().map(
                e -> ExceptionUtils.buildErrorDetails(e.getDefaultMessage(), ((FieldError) e).getField(), exception))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Error::getErrorCode))))
                .stream().collect(Collectors.toList());

        exception.setSource(ExceptionUtils.getDefaultProjectName());
        exception.setErrors(errorsList);
        return handleException(request, exception);
    }

    /**
     * @param request <b>HttpServletRequest</b>
     * @param ex      <b>ServiceException</b>
     * @return <b>ResponseEntity&lt<span>ErrorResponse</span>&gt</b>
     */
    @Nullable
    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, final ServiceException ex) {
        log.error("{} exception -> code: {}, message: {}", StringUtils.capitalize(ex.getCategory().getName()), ex.getCategory().getCode(), ex.getMessage(), ex);
        ex.setUrl("");
        if (ex.getErrors().isEmpty()) {
            ex.getErrors().add(ExceptionUtils.buildErrorDetails(ex.getClass().getSimpleName(), ex));
            ex.setSource(getExceptionSource(ex));
        }

        ErrorResponse errorResponse = new ErrorResponse(ex);
        HttpHeaders headers = ExceptionUtils.getExceptionHttpHeaders(ex);

        return new ResponseEntity<>(errorResponse, headers, HttpStatus.resolve(ex.getStatusCode()));
    }

    /**
     * @param ex
     * @return
     */
    private String getExceptionSource(Exception ex) {
        try {
            return MessageResourceBundle.getSource(ex.getClass().getSimpleName());
        } catch (Exception e) {
            return ExceptionUtils.getDefaultProjectName();
        }
    }

    /**
     * @param exceptionPrefix
     * @param ex
     * @return
     */
    private String getExceptionSource(String exceptionPrefix, Exception ex) {
        try {
            return MessageResourceBundle.getSource(exceptionPrefix.concat(ex.getClass().getSimpleName()));
        } catch (Exception e) {
            return ExceptionUtils.getDefaultProjectName();
        }
    }

    private void logStackTrace(Throwable ex, String errorId) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        log.error(errorId + " : " + sw.toString());
    }
}

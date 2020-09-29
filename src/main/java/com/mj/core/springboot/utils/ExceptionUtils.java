package com.mj.core.springboot.utils;

import com.mj.core.springboot.configuration.ConfigurableEnvironmentConfig;
import com.mj.core.springboot.exception.ServiceException;
import com.mj.core.springboot.model.Error;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 2/23/19
 */

public final class ExceptionUtils {

    private static final String ERROR_CODE_TEMPLATE = "%s.%s.%s";

    private static final ConfigurableEnvironment environment = ConfigurableEnvironmentConfig.getInstance();

    private ExceptionUtils() {
        //Empty constructor
    }

    public static HttpHeaders getExceptionHttpHeaders(ServiceException exception) {
        StringJoiner joiner = new StringJoiner(".");

        Optional<String> projectName = ExceptionUtils.getProjectName();

        projectName.ifPresent(s -> joiner.add(s.toUpperCase()));
        joiner.add(exception.getSource().toUpperCase()).add(exception.getCategory().getName().toUpperCase());

        HttpHeaders headers = new HttpHeaders();
        headers.add("error-code", joiner.toString());

        return headers;
    }

    public static Optional<String> getProjectName() {
        return Optional.ofNullable(environment.getProperty("config.project-name"));
    }

    public static String getDefaultProjectName() {
        return environment.getProperty("spring.application.name");
    }

    public static Error buildErrorDetails(String exceptionName, ServiceException ex) {
        return buildErrorDetails(exceptionName, "", ex);
    }

    public static <T> Error buildErrorDetails(String exceptionName, ServiceException ex, T errorDetails) {
        return buildErrorDetails(exceptionName, "", ex, errorDetails);
    }

    public static <T> Error buildErrorDetails(String exceptionName, String path, ServiceException ex) {
        return buildErrorDetails(exceptionName, path, ex, null);
    }

    public static <T> Error buildErrorDetails(String exceptionName, String path, ServiceException ex, T errorDetails) {

        String errorMessage;
        String errorCode;
        String errorPath;

        // Read Error Code
        try {
            errorCode = MessageResourceBundle.getCode(exceptionName);
        } catch (Exception e) {
            errorCode = "***";
        }

        // Read Error Message
        try {
            errorMessage = MessageResourceBundle.getMessage(exceptionName);
        } catch (Exception e) {
            errorMessage = ex.getMessage();
        }

        // Read Error Path
        try {
            if (StringUtils.isEmpty(path))
                errorPath = MessageResourceBundle.getPath(exceptionName);
            else
                errorPath = path;
        } catch (Exception e) {
            errorPath = "";
        }

        // Return the Error object
        return new Error(buildErrorCode(ex, errorCode), errorMessage, errorPath, errorDetails);
    }

    public static String buildErrorCode(ServiceException ex, String errorCode) {
        if (Objects.nonNull(ex)) {
            return String.format(ERROR_CODE_TEMPLATE, ex.getStatusCode(), ex.getCategory().getCode(), errorCode);
        }
        return String.format(ERROR_CODE_TEMPLATE, "***", "***", errorCode);
    }

}

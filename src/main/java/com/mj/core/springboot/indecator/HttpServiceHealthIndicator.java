package com.mj.core.springboot.indecator;

import lombok.NonNull;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class HttpServiceHealthIndicator implements HealthIndicator {

    protected final String serviceUrl;
    protected final HttpStatus httpStatusToCheck;
    protected final RestTemplate restTemplate = new RestTemplate();


    public HttpServiceHealthIndicator(@NonNull String serviceUrl, @NonNull HttpStatus httpStatusToCheck) {
        this.serviceUrl = serviceUrl;
        this.httpStatusToCheck = httpStatusToCheck;
        this.restTemplate.setErrorHandler(buildPassResponseErrorHandler());
    }

    private ResponseErrorHandler buildPassResponseErrorHandler() {
        return new DefaultResponseErrorHandler() {
            @Override
            protected void handleError(ClientHttpResponse response, HttpStatus statusCode) {
                // Overrides the throw server or service Error Exception
            }
        };
    }

    @Override
    public Health health() {
        Boolean result = checkIfServiceIsUp();

        if (!result) {
            return Health.down().withDetail("Http service is down , url : " + serviceUrl, true).build();
        }

        return Health.up().build();
    }

    protected Boolean checkIfServiceIsUp() {
        HttpStatus statusCode;
        try {

            ResponseEntity<String> response = restTemplate.getForEntity(URI.create(serviceUrl), String.class);
            statusCode = response.getStatusCode();
        } catch (Exception e) {
            statusCode = null;
        }

        return httpStatusToCheck.equals(statusCode);
    }
}
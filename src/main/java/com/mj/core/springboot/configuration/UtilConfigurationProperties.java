package com.mj.core.springboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "exception.util")
public class UtilConfigurationProperties {

    ExceptionAdvice exceptionAdvice;

    public ExceptionAdvice getExceptionAdvice() {
        return exceptionAdvice;
    }

    public void setExceptionAdvice(ExceptionAdvice exceptionAdvice) {
        this.exceptionAdvice = exceptionAdvice;
    }

    class ExceptionAdvice {

        boolean enabled = true;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}

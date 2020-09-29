package com.mj.core.springboot.configuration;

import com.mj.core.springboot.exception.advice.ExceptionAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Configuration
@SuppressWarnings("SpellCheckingInspection")
@ConditionalOnProperty(prefix = "util.exception-advice", name = "enabled", matchIfMissing = true)
@ConditionalOnMissingBean(annotation = ControllerAdvice.class)
public class ControllerAdviceConfiguration {

    /**
     * @return a new ExceptionAdvisor instance
     */
    @Bean
    public ExceptionAdvisor defaultAdvisor() {
        return new ExceptionAdvisor() {
        };
    }
}

package com.mj.microservice.core.configuration;

import com.mj.microservice.core.service.event.DefaultEventSender;
import com.mj.microservice.core.service.event.EventSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project springboot-ms-core
 * @Author Montaser Sobaih
 * @Date 29-09-2020
 */

@Configuration
@ConditionalOnMissingBean(EventSender.class)
public class EventSenderConfiguration {

    @Bean
    public EventSender sender() {
        return new DefaultEventSender();
    }
}

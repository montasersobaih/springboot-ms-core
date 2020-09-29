package com.mj.core.springboot.utils;

import com.mj.core.springboot.configuration.ConfigurableEnvironmentConfig;
import com.mj.core.springboot.constant.Constants;
import org.springframework.integration.support.MessageBuilder;

import java.util.Objects;

public class MessageBuilderFactory {
    public static <T> MessageBuilder<T> getMessageBuilder(T payload) {
        if (Objects.isNull(CommonContext.getRequestInfo())) {
            // set common headers
            return MessageBuilder.withPayload(payload);
        }

        return MessageBuilder.withPayload(payload)
                .setHeaderIfAbsent(Constants.REQUEST_DASH_ID, CommonContext.getRequestInfo().getRequestId())
                .setHeaderIfAbsent(Constants.CHANNEL_DASH_NAME, CommonContext.getRequestInfo().getChannelName())
                .setHeaderIfAbsent(Constants.COUNTRY_DASH_CODE, CommonContext.getRequestInfo().getCountryCode())
                .setHeaderIfAbsent(Constants.APPLICATION_DASH_NAME, ConfigurableEnvironmentConfig.getInstance().getProperty("spring.application.name"));
    }
}


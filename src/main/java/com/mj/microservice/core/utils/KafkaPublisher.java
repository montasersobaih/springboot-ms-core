package com.mj.microservice.core.utils;

import com.mj.microservice.core.channel.GeneralChannel;
import com.mj.microservice.core.exception.TechnicalException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class KafkaPublisher {

    private KafkaPublisher() {}

    public static <T> void send(GeneralChannel channel, T event) {
        log.info("Publishing ".concat(event.getClass().getSimpleName()).concat(" event, "), event);

        if (channel.messageChannel().send(MessageBuilderFactory.getMessageBuilder(event).build())) {
            log.info("Publishing {} event has been published to kafka successfully", event.getClass().getSimpleName());
        } else {
            throw new TechnicalException("Error while sending ".concat(event.getClass().getSimpleName()).concat(" event to kafka."));
        }
    }
}

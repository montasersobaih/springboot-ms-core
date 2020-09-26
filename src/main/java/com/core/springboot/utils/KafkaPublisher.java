package com.core.springboot.utils;

import com.core.springboot.channel.KafkaChannel;
import com.core.springboot.exception.TechnicalException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class KafkaPublisher {

    private KafkaPublisher() {
        //Empty Constructor
    }

    public static <T> void send(KafkaChannel channel, T event) {
        log.info("Publishing ".concat(event.getClass().getSimpleName()).concat(" event, "), event);

        if (channel.messageChannel().send(MessageBuilderFactory.getMessageBuilder(event).build()))
            log.info("Publishing {} event has been published to kafka successfully", event.getClass().getSimpleName());
        else
            throw new TechnicalException("Error while sending ".concat(event.getClass().getSimpleName()).concat(" event to kafka."));
    }
}

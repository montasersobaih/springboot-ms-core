package com.mj.microservice.core.service.event;

import com.mj.microservice.core.channel.GeneralChannel;
import com.mj.microservice.core.model.event.Event;
import com.mj.microservice.core.utils.MessageBuilderFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.Message;

/**
 * @Project springboot-ms-core
 * @Author Montaser Sobaih
 * @Date 29-09-2020
 */

@Slf4j
public class DefaultEventSender implements EventSender, ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public <T> boolean send(Event<T> event) {
        Message<Event<T>> message = MessageBuilderFactory.getMessageBuilder(event).build();

        try {
            return context.getBean(event.getChannelName(), GeneralChannel.class).messageChannel().send(message);
        } catch (Exception ex) {
            log.error("No event channel found to send data");
            return false;
        }
    }
}

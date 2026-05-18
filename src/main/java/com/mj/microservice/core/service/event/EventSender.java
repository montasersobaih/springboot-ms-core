package com.mj.microservice.core.service.event;

import com.mj.microservice.core.model.event.Event;

/**
 * @Project springboot-ms-core
 * @Author Montaser Sobaih
 * @Date 29-09-2020
 */

public interface EventSender {

    <T> boolean send(Event<T> event);
}

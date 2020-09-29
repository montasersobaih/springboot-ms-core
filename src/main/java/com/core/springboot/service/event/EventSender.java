package com.core.springboot.service.event;

import com.core.springboot.model.event.Event;

/**
 * @Project springboot-ms-core
 * @Author Montaser Sobaih
 * @Date 29-09-2020
 */

public interface EventSender {

    <T extends Event<T>> boolean send(T event);
}

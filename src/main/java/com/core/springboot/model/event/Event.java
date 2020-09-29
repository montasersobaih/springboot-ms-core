package com.core.springboot.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project springboot-ms-core
 * @Author Montaser Sobaih
 * @Date 29-09-2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Event<T> {

    private EventHeader header;

    private T body;

    public final String getChannelName() {
        return getClass().getSimpleName().concat("Channel");
    }
}

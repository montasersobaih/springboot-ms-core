package com.mj.core.springboot.model.event;

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
public class EventHeader {

    private String source;

    private EventAction eventAction;
}

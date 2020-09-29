package com.mj.core.springboot.model.event;

/**
 * <b>Note:-</b> This interface is used only for implements EventAction enums.
 *
 * @Project springboot-ms-core
 * @Author Montaser Sobaih
 * @Date 29-09-2020
 */

public interface EventAction<R> {

    R getAction();
}

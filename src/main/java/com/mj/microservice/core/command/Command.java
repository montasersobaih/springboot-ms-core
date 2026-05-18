package com.mj.microservice.core.command;

import com.mj.microservice.core.utils.StringUtils;

public interface Command {

    default String getHandlerName() {
        return String.format("%sHandler", StringUtils.capitalize(getClass().getSimpleName()));
    }
}

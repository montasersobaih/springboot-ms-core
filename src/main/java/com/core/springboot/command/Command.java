package com.core.springboot.command;

import com.core.springboot.utils.StringUtil;

public interface Command {

    default String getHandlerName() {
        return String.format("%sHandler", StringUtil.capitalize(getClass().getSimpleName()));
    }
}

package com.mj.core.springboot.command;

import com.mj.core.springboot.utils.StringUtil;

public interface Command {

    default String getHandlerName() {
        return String.format("%sHandler", StringUtil.capitalize(getClass().getSimpleName()));
    }
}

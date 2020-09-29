package com.mj.core.springboot.command;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DefaultCommandExecutor implements CommandExecutor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Command, R> R execute(T command) {
        return ((CommandHandler<T, R>) applicationContext.getBean(command.getHandlerName())).handle(command);
    }
}

package com.mj.microservice.core.command;

public interface CommandExecutor {

    <T extends Command, R> R execute(T command);
}

package com.core.springboot.command;

public interface CommandExecutor {

    <T extends Command, R> R execute(T command);
}

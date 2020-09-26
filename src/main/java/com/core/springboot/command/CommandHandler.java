package com.core.springboot.command;

public interface CommandHandler<T extends Command, R> {

    R handle(T command);
}

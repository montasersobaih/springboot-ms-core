package com.mj.microservice.core.command;

public interface CommandHandler<T extends Command, R> {

    R handle(T command);
}

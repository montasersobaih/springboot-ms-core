package com.mj.microservice.core.configuration;

import com.mj.microservice.core.command.CommandExecutor;
import com.mj.microservice.core.command.DefaultCommandExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(CommandExecutor.class)
public class CommandExecutorConfiguration {

    /**
     * @return a default command executor
     */
    @Bean
    public CommandExecutor createCommandExecutor() {
        return new DefaultCommandExecutor();
    }
}

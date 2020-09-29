package com.mj.core.springboot.configuration;

import com.mj.core.springboot.command.CommandExecutor;
import com.mj.core.springboot.command.DefaultCommandExecutor;
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

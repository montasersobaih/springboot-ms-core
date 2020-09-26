package com.core.springboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class ConfigurableEnvironmentConfig {

    private static ConfigurableEnvironment environment;

    public ConfigurableEnvironmentConfig(ConfigurableEnvironment environment) {
        ConfigurableEnvironmentConfig.environment = environment;
    }

    public static ConfigurableEnvironment getInstance() {
        return ConfigurableEnvironmentConfig.environment;
    }
}

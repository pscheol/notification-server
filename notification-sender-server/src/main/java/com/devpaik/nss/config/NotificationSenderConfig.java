package com.devpaik.nss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class NotificationSenderConfig {

    @Bean("eventSMSExecutor")
    public Executor eventSMSExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int processor = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(processor);
        executor.setMaxPoolSize(processor * (1 + 3000 / 1000));
        executor.setThreadNamePrefix("event-sms-");
        executor.initialize();
        return executor;
    }

    @Bean("eventEmailExecutor")
    public Executor eventEmailExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int processor = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(processor);
        executor.setMaxPoolSize(processor * (1 + 3000 / 1000));
        executor.setThreadNamePrefix("event-email-");
        executor.initialize();
        return executor;
    }

    @Bean("eventPushExecutor")
    public Executor eventPushExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int processor = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(processor);
        executor.setMaxPoolSize(processor * (1 + 3000 / 1000));
        executor.setThreadNamePrefix("event-push-");
        executor.initialize();
        return executor;
    }
}

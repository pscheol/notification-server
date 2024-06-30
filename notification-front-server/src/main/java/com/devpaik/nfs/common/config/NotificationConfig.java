package com.devpaik.nfs.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class NotificationConfig {

//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        return objectMapper;
//    }

    @Bean("notificationSendEventExecutor")
    public Executor notificationSendEventExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int processor = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(processor);
        executor.setMaxPoolSize(processor * (1 + 3000 / 1000));
        executor.setThreadNamePrefix("send-executor-");
        executor.initialize();
        return executor;
    }

    @Bean("notificationSendSMSExecutor")
    public Executor notificationSendSMSExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int processor = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(processor);
        executor.setMaxPoolSize(processor * (1 + 3000 / 1000));
        executor.setThreadNamePrefix("sms-executor-");
        executor.initialize();
        return executor;
    }

    @Bean("notificationSendEmailExecutor")
    public Executor notificationSendEmailExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int processor = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(processor);
        executor.setMaxPoolSize(processor * (1 + 3000 / 1000));
        executor.setThreadNamePrefix("email-executor-");
        executor.initialize();
        return executor;
    }

    @Bean("notificationSendPushExecutor")
    public Executor notificationSendPushExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int processor = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(processor);
        executor.setMaxPoolSize(processor * (1 + 3000 / 1000));
        executor.setThreadNamePrefix("push-executor-");
        executor.initialize();
        return executor;
    }
}

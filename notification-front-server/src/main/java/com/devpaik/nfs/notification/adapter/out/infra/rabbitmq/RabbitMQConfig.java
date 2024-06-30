package com.devpaik.nfs.notification.adapter.out.infra.rabbitmq;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitMQConfig implements RabbitListenerConfigurer {

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("exchange.notification");
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }


    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public Queue queueSMS() {
        return QueueBuilder
                .durable("queue.notification.sms")
                .build();
    }
    @Bean
    public Binding bindingSMS(TopicExchange topicExchange, Queue queueSMS) {
        return BindingBuilder.bind(queueSMS)
                .to(topicExchange)
                .with("notification.sms");
    }

    @Bean
    public Queue queueEmail() {
        return QueueBuilder
                .durable("queue.notification.email")
                .autoDelete()
                .build();
    }
    @Bean
    public Binding bindingEmail(TopicExchange topicExchange, Queue queueEmail) {
        return BindingBuilder.bind(queueEmail)
                .to(topicExchange)
                .with("notification.email");
    }
    @Bean
    public Queue queuePush() {
        return QueueBuilder
                .durable("queue.notification.push")
                .autoDelete()
                .build();
    }
    @Bean
    public Binding bindingPush(TopicExchange topicExchange, Queue queuePush) {
        return BindingBuilder.bind(queuePush)
                .to(topicExchange)
                .with("notification.push");
    }


    @Bean
    public Queue queueNotificationResult() {
        return QueueBuilder
                .durable("queue.notification.result")
                .build();
    }

    @Bean
    public Binding bindingFrontNotificationSuccess(TopicExchange topicFrontExchange, Queue queueNotificationResult) {
        return BindingBuilder.bind(queueNotificationResult)
                .to(topicFrontExchange)
                .with("notification.success");
    }

    @Bean
    public Binding bindingFrontNotificationFail(TopicExchange topicFrontExchange, Queue queueNotificationResult) {
        return BindingBuilder.bind(queueNotificationResult)
                .to(topicFrontExchange)
                .with("notification.fail");
    }
}

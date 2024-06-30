package com.devpaik.nfs.notification.adapter.out.infra.rabbitmq;

import com.devpaik.nfs.notification.adapter.out.infra.rabbitmq.dto.EmailMessage;
import com.devpaik.nfs.notification.adapter.out.infra.rabbitmq.dto.MessageMapper;
import com.devpaik.nfs.notification.adapter.out.infra.rabbitmq.dto.PushMessage;
import com.devpaik.nfs.notification.adapter.out.infra.rabbitmq.dto.SMSMessage;
import com.devpaik.nfs.notification.application.port.out.SendNotificationClientPort;
import com.devpaik.nfs.notification.domain.EventId;
import com.devpaik.nfs.notification.domain.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * topic : exchange.notification
 * routing key = notification.sms
 * routing key = notification.push
 * routing key = notification.email
 */
@Slf4j
@Component
public class SendNotificationProducer implements SendNotificationClientPort {

    private final MessageMapper messageMapper;
    private final RabbitTemplate rabbitTemplate;

    public SendNotificationProducer(MessageMapper messageMapper, RabbitTemplate rabbitTemplate) {
        this.messageMapper = messageMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async("notificationSendEmailExecutor")
    @Override
    public void sendNotificationEmailClient(EventId eventId, Notification notification) {
        log.debug("sendEmail...");
        sendMessage("notification.email", messageMapper.parseJson(EmailMessage.of(eventId.getId(), notification)));

    }

    @Async("notificationSendPushExecutor")
    @Override
    public void sendNotificationPushClient(EventId eventId, Notification notification) {
        log.debug("sendPush...");
        sendMessage("notification.push", messageMapper.parseJson(PushMessage.of(eventId.getId(), notification)));
    }

    @Async("notificationSendSMSExecutor")
    @Override
    public void sendNotificationSMSClient(EventId eventId, Notification notification) {
        log.debug("sendSMS...");
        sendMessage("notification.sms", messageMapper.parseJson(SMSMessage.of(eventId.getId(), notification)));
    }


    private void sendMessage(String routingKey, String jsonData) {
        rabbitTemplate.convertAndSend("exchange.notification",
                routingKey, jsonData);
    }
}

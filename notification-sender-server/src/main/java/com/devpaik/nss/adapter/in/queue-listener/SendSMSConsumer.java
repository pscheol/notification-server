package com.devpaik.nss.consumer;

import com.devpaik.nss.dto.MessageMapper;
import com.devpaik.nss.application.event.EventPublisher;
import com.devpaik.nss.application.event.SendSMSEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendSMSConsumer {

    private final MessageMapper messageMapper;
    private final EventPublisher eventPublisher;

    public SendSMSConsumer(MessageMapper messageMapper, EventPublisher eventPublisher) {
        this.messageMapper = messageMapper;
        this.eventPublisher = eventPublisher;
    }

    @RabbitListener(queues = "queue.notification.sms")
    public void receive(String src) {
        eventPublisher.publishEvent(new SendSMSEvent(messageMapper.jsonToSMSMessage(src)));
    }
}

package com.devpaik.nss.consumer;

import com.devpaik.nss.dto.MessageMapper;
import com.devpaik.nss.application.event.EventPublisher;
import com.devpaik.nss.application.event.SendPushEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendPushConsumer {
    private final MessageMapper messageMapper;
    private final EventPublisher eventPublisher;

    public SendPushConsumer(MessageMapper messageMapper, EventPublisher eventPublisher) {
        this.messageMapper = messageMapper;
        this.eventPublisher = eventPublisher;
    }

    @RabbitListener(queues = "queue.notification.push")
    public void receive(String src) {
        eventPublisher.publishEvent(new SendPushEvent(messageMapper.jsonToPushMessage(src)));
    }
}

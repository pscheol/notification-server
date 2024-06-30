package com.devpaik.nss.consumer;

import com.devpaik.nss.dto.MessageMapper;
import com.devpaik.nss.event.EventPublisher;
import com.devpaik.nss.event.SendEmailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendEmailConsumer {

    private final MessageMapper messageMapper;
    private final EventPublisher eventPublisher;

    public SendEmailConsumer(MessageMapper messageMapper, EventPublisher eventPublisher) {
        this.messageMapper = messageMapper;
        this.eventPublisher = eventPublisher;
    }

    @RabbitListener(queues = "queue.notification.email")
    public void receive(String src) {
        eventPublisher.publishEvent(new SendEmailEvent(messageMapper.jsonToEmailMessage(src)));
    }
}

package com.devpaik.nfs.notification.adapter.out.infra.rabbitmq;

import com.devpaik.nfs.notification.adapter.out.infra.rabbitmq.dto.MessageMapper;
import com.devpaik.nfs.notification.adapter.out.infra.rabbitmq.dto.ResultMessage;
import com.devpaik.nfs.notification.application.port.in.ResultNotificationUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationConsumer {
    private final MessageMapper messageMapper;
    private final ResultNotificationUseCase resultNotificationUseCase;

    public NotificationConsumer(MessageMapper messageMapper, ResultNotificationUseCase resultNotificationUseCase) {
        this.messageMapper = messageMapper;
        this.resultNotificationUseCase = resultNotificationUseCase;
    }

    @RabbitListener(queues = "queue.notification.result")
    public void receive(String src) {
        ResultMessage resultMessage = messageMapper.jsonToResultMessage(src);
        log.debug("resultMessage={}", resultMessage);

        if ("SUCCESS".equals(resultMessage.resultCode())) {
            resultNotificationUseCase.responseNotificationBySuccess(resultMessage.eventId());
        } else {
            resultNotificationUseCase.responseNotificationByFail(resultMessage.eventId());
        }
    }
}

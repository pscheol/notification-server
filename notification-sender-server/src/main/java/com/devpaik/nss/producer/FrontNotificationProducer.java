package com.devpaik.nss.producer;

import com.devpaik.nss.dto.MessageMapper;
import com.devpaik.nss.dto.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class FrontNotificationProducer implements SendFrontNotificationBySuccess, SendFrontNotificationByFail {
    private final MessageMapper messageMapper;
    private final RabbitTemplate rabbitTemplate;

    public FrontNotificationProducer(MessageMapper messageMapper, RabbitTemplate rabbitTemplate) {
        this.messageMapper = messageMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendFailMessage(Long eventId, String resultCode) {
        log.debug("# front sendMessage...event Id={}, resultCode={}", eventId, resultCode);
        sendMessage("notification.fail", messageMapper.resultMessageToJson(new ResultMessage(eventId, resultCode)));
    }
    public void sendSuccessMessage(Long eventId, String resultCode) {
        log.debug("# front sendMessage...event Id={}, resultCode={}", eventId, resultCode);
        sendMessage("notification.success", messageMapper.resultMessageToJson(new ResultMessage(eventId, resultCode)));
    }

    private void sendMessage(String routingKey, String jsonData) {
        rabbitTemplate.convertAndSend("exchange.front.notification",
                routingKey, jsonData);
    }
}

package com.devpaik.nss.application.event.handler;

import com.devpaik.nss.application.event.EventPublisher;
import com.devpaik.nss.application.event.SendPushEvent;
import com.devpaik.nss.application.event.SendResultEvent;
import com.devpaik.nss.simulation.NotificationSender;
import com.devpaik.nss.simulation.ResponseTypeSelector;
import com.devpaik.nss.simulation.ResultType;
import com.devpaik.nss.simulation.SendResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendPushEventHandler {
    private final NotificationSender sender = new NotificationSender();
    private final EventPublisher eventPublisher;

    public SendPushEventHandler(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Async("eventPushExecutor")
    @EventListener(SendPushEvent.class)
    public void sendPushEventHandler(SendPushEvent sendPushEvent) {
        log.debug("# sendPushEventHandler={}", sendPushEvent);

        //test simulation code
        final ResponseTypeSelector selector = new ResponseTypeSelector();
        String result = sendResponse(selector);

        eventPublisher.publishEvent(new SendResultEvent(sendPushEvent.message().eventId(), result));
    }

    private String sendResponse(ResponseTypeSelector selector) {
        String result = ResultType.FAIL.getResult();
        try {
            SendResponse response = sender.send(selector);
            result = response.resultCode();
        } catch (Exception e) {
            log.error("Send Notification Fail...", e);
        }
        return result;
    }
}

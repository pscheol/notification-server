package com.devpaik.nss.application.event.handler;

import com.devpaik.nss.application.event.EventPublisher;
import com.devpaik.nss.application.event.SendResultEvent;
import com.devpaik.nss.application.event.SendSMSEvent;
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
public class SendSMSEventHandler {
    private final NotificationSender sender = new NotificationSender();
    private final EventPublisher eventPublisher;

    public SendSMSEventHandler(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Async("eventSMSExecutor")
    @EventListener(SendSMSEvent.class)
    public void sendSMSEventHandler(SendSMSEvent sendSMSEvent) {
        log.debug("# sendSMSEventHandler={}", sendSMSEvent);

        //test simulation code
        final ResponseTypeSelector selector = new ResponseTypeSelector();
        String result = sendResponse(selector);

        eventPublisher.publishEvent(new SendResultEvent(sendSMSEvent.message().eventId(), result));
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

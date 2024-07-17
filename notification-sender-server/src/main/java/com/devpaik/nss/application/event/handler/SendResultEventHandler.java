package com.devpaik.nss.application.event.handler;

import com.devpaik.nss.adapter.out.producer.SendFrontNotificationByFail;
import com.devpaik.nss.adapter.out.producer.SendFrontNotificationBySuccess;
import com.devpaik.nss.application.event.SendResultEvent;
import com.devpaik.nss.simulation.SendResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendResultEventHandler {
    private final SendFrontNotificationBySuccess sendFrontNotificationBySuccess;
    private final SendFrontNotificationByFail sendFrontNotificationByFail;

    public SendResultEventHandler(SendFrontNotificationBySuccess sendFrontNotificationBySuccess, SendFrontNotificationByFail sendFrontNotificationByFail) {
        this.sendFrontNotificationBySuccess = sendFrontNotificationBySuccess;
        this.sendFrontNotificationByFail = sendFrontNotificationByFail;
    }

    @EventListener(SendResultEvent.class)
    public void sendResultEventHandler(SendResultEvent sendResultEvent) {
        log.debug("# sendResultEventHandler={}", sendResultEvent);

        if (SendResponse.SUCCESS.equals(sendResultEvent.resultCode())) {
            sendFrontNotificationBySuccess.sendSuccessMessage(sendResultEvent.eventId(), sendResultEvent.resultCode());
        } else {
            sendFrontNotificationByFail.sendFailMessage(sendResultEvent.eventId(), sendResultEvent.resultCode());
        }
    }
}

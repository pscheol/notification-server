package com.devpaik.nss.event.handler;

import com.devpaik.nss.event.SendEmailEvent;
import com.devpaik.nss.event.SendPushEvent;
import com.devpaik.nss.event.SendSMSEvent;
import com.devpaik.nss.producer.SendFrontNotificationByFail;
import com.devpaik.nss.producer.SendFrontNotificationBySuccess;
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
public class SendEventHandler {
    private final NotificationSender sender = new NotificationSender();
    private final SendFrontNotificationBySuccess sendFrontNotificationBySuccess;
    private final SendFrontNotificationByFail sendFrontNotificationByFail;

    public SendEventHandler(SendFrontNotificationBySuccess sendFrontNotificationBySuccess, SendFrontNotificationByFail sendFrontNotificationByFail) {
        this.sendFrontNotificationBySuccess = sendFrontNotificationBySuccess;
        this.sendFrontNotificationByFail = sendFrontNotificationByFail;
    }

    @Async("eventPushExecutor")
    @EventListener(SendPushEvent.class)
    public void sendPushEventHandler(SendPushEvent sendPushEvent) {
        log.debug("# sendPushEventHandler={}", sendPushEvent);

        final ResponseTypeSelector selector = new ResponseTypeSelector();

        String result = sendResponse(selector);

        sendFrontNotification(sendPushEvent.message().eventId(), result);
    }

    @Async("eventSMSExecutor")
    @EventListener(SendSMSEvent.class)
    public void sendSMSEventHandler(SendSMSEvent sendSMSEvent) {
        log.debug("# sendSMSEventHandler={}", sendSMSEvent);

        final ResponseTypeSelector selector = new ResponseTypeSelector();

        String result = sendResponse(selector);

        sendFrontNotification(sendSMSEvent.message().eventId(), result);
    }

    @Async("eventEmailExecutor")
    @EventListener(SendEmailEvent.class)
    public void sendEmailEventHandler(SendEmailEvent sendEmailEvent) {
        log.debug("# sendEmailEventHandler={}", sendEmailEvent);

        final ResponseTypeSelector selector = new ResponseTypeSelector();

        String result = sendResponse(selector);

        sendFrontNotification(sendEmailEvent.message().eventId(), result);
    }

    private void sendFrontNotification(Long eventId, String resultCode) {
        log.debug("# response={}", resultCode);

        if (SendResponse.SUCCESS.equals(resultCode)) {
            sendFrontNotificationBySuccess.sendSuccessMessage(eventId, resultCode);
        } else {
            sendFrontNotificationByFail.sendFailMessage(eventId, resultCode);
        }
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

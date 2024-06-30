package com.devpaik.nfs.notification.adapter.out.handler;

import com.devpaik.nfs.notification.application.port.out.NotificationEventPort;
import com.devpaik.nfs.notification.domain.EventType;
import com.devpaik.nfs.notification.domain.event.NotificationEvent;
import com.devpaik.nfs.notification.domain.event.SendNotificationEvent;
import com.devpaik.nfs.notification.domain.event.StartNotificationSendEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class StartNotificationEventHandler {

    private final NotificationEventPort notificationEventPort;


    public StartNotificationEventHandler(NotificationEventPort notificationEventPort) {
        this.notificationEventPort = notificationEventPort;
    }

    @EventListener(classes = StartNotificationSendEvent.class)
    public void startNotificationSendEventHandler(StartNotificationSendEvent event) {
        log.debug("startNotificationSendEventHandler={}", event);
        NotificationEvent notificationEvent = event.notificationEvent();

        if ((EventType.NOW.equals(notificationEvent.getEventType()))) {
            notificationEventPort.publishNotificationEvent(SendNotificationEvent.of(notificationEvent));
        }
    }

}

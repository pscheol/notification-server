package com.devpaik.nfs.notification.adapter.out.infra.schedule;

import com.devpaik.nfs.notification.application.port.out.EventNotificationPort;
import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.EventType;
import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.State;
import com.devpaik.nfs.notification.application.port.out.NotificationEventPort;
import com.devpaik.nfs.notification.domain.event.NotificationEvent;
import com.devpaik.nfs.notification.domain.event.SendNotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class NotificationEventSchedule {

    private final EventNotificationPort eventStore;
    private final NotificationEventPort notificationEventPort;

    private static final int LIMIT_SIZE = 200;
    private static final int DEFAULT_OFFSET = 0;

    public NotificationEventSchedule(EventNotificationPort eventStore, NotificationEventPort notificationEventPort) {
        this.eventStore = eventStore;
        this.notificationEventPort = notificationEventPort;
    }



    @Transactional(readOnly = true)
    @Scheduled(fixedDelay = 5000)
    public void reservationNotificationSchedule() {
        log.debug("@@ reservationNotificationSchedule start..");
        final LocalDateTime dateTime = LocalDateTime.now();

        List<NotificationEvent> notificationEvents = eventStore.findByAllStateAndEventTypeAndDateTime(State.REQ, EventType.SCHEDULE, dateTime, DEFAULT_OFFSET, LIMIT_SIZE);

        log.debug("reservationNotificationSchedule notificationEvents size={}", notificationEvents.size());

        if (notificationEvents.isEmpty()) {
            return;
        }

        publishNotificationEvent(notificationEvents);
    }


    @Transactional(readOnly = true)
    @Scheduled(fixedDelay = 8000)
    public void failedNotificationSchedule() {
        log.debug("@@ failedNotificationSchedule start..");
        final LocalDateTime dateTime = LocalDateTime.now();

        List<NotificationEvent> notificationEvents = eventStore.findByAllStateAndDateTime(State.FAIL, dateTime, DEFAULT_OFFSET, LIMIT_SIZE);

        log.debug("failedNotificationSchedule notificationEvents size={}", notificationEvents.size());
        if (notificationEvents.isEmpty()) {
            return;
        }

        publishNotificationEvent(notificationEvents);
    }

    private void publishNotificationEvent(List<NotificationEvent> notificationEvents) {
        for (NotificationEvent notificationEvent : notificationEvents) {
            notificationEventPort.publishNotificationEvent(SendNotificationEvent.of(notificationEvent));
        }
    }
}

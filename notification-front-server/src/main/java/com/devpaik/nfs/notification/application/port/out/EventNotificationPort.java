package com.devpaik.nfs.notification.application.port.out;

import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.EventType;
import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.State;
import com.devpaik.nfs.notification.domain.event.NotificationEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface EventNotificationPort {

    NotificationEvent save(NotificationEvent notificationEvent);

    void updateState(NotificationEvent notificationEvent);

    List<NotificationEvent> findByAllStateAndEventTypeAndDateTime(State state, EventType eventType,
                                                                        LocalDateTime dateTime, int offset, int limit);
    List<NotificationEvent> findByAllStateAndDateTime(State state, LocalDateTime dateTime, int offset, int limit);

    NotificationEvent findById(Long eventId);
}

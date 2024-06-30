package com.devpaik.nfs.notification.adapter.out.infra.event;

import com.devpaik.nfs.notification.application.port.out.NotificationEventPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
class NotificationEventPublisher implements NotificationEventPort {
    private final ApplicationEventPublisher publisher;

    public NotificationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishNotificationEvent(Object event) {
        if (this.publisher != null) {
            publisher.publishEvent(event);
        }
    }
}

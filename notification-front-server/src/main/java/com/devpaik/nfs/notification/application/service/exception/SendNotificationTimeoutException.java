package com.devpaik.nfs.notification.application.service.exception;

import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.State;
import com.devpaik.nfs.notification.domain.EventId;
import lombok.Getter;

public class SendNotificationTimeoutException extends RuntimeException {
    @Getter
    private EventId eventId;
    @Getter
    private State state;

    public SendNotificationTimeoutException(EventId eventId) {
        super("Send Notification Timeout..");
        this.eventId = eventId;
        this.state = State.FAIL;
    }
}

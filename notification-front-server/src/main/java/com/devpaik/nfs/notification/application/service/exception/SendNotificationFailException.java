package com.devpaik.nfs.notification.application.service.exception;

import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.State;
import com.devpaik.nfs.notification.domain.EventId;

public class SendNotificationFailException extends RuntimeException {
    private EventId eventId;
    private State state;

    public SendNotificationFailException(EventId eventId) {
        super("Send Notification Fail..");
        this.eventId = eventId;
        this.state = State.FAIL;
    }
}

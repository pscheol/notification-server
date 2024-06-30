package com.devpaik.nfs.notification.adapter.out.persistence.event;

public class NotFoundNotificationEventException extends RuntimeException {
    public NotFoundNotificationEventException() {
        super("Not found notificationEvent data.");
    }
}

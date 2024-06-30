package com.devpaik.nfs.notification.application.port.out;

import com.devpaik.nfs.notification.domain.EventId;
import com.devpaik.nfs.notification.domain.Notification;

public interface SendNotificationClientPort {
    void sendNotificationEmailClient(EventId eventId, Notification notification);

    void sendNotificationPushClient(EventId eventId, Notification notification);

    void sendNotificationSMSClient(EventId eventId, Notification notification);
}

package com.devpaik.nfs.notification.domain.event;


import com.devpaik.nfs.notification.domain.*;

public record SendNotificationEvent(EventId eventId, Receiver receiver, Message message, Channel channel) {

    public static SendNotificationEvent of(NotificationEvent notificationEvent) {
        Notification notification = notificationEvent.getEventData();
        return new SendNotificationEvent(
                notificationEvent.getEventId(),
                notification.getReceiver(),
                notification.getMessage(),
                notification.getChannel());
    }
}

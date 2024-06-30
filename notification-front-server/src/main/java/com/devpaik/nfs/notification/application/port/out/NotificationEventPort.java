package com.devpaik.nfs.notification.application.port.out;

public interface NotificationEventPort {
    void publishNotificationEvent(Object event);
}

package com.devpaik.nfs.notification.application.port.out;

import com.devpaik.nfs.notification.domain.Notification;

public interface InsertNotificationPort {

    void saveNotification(Notification notification);

}

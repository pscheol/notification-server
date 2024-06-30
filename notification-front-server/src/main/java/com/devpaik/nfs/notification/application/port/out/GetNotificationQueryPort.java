package com.devpaik.nfs.notification.application.port.out;

import com.devpaik.nfs.notification.domain.query.NotificationView;

import java.util.List;

public interface GetNotificationQueryPort {

    public List<NotificationView> selectNotificationSuccessList(int page, int limit);

    long totalNotificationSuccess();
}

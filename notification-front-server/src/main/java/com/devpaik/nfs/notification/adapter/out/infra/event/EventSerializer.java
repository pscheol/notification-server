package com.devpaik.nfs.notification.adapter.out.infra.event;

import com.devpaik.nfs.notification.domain.Notification;


public interface EventSerializer {
    String serialize(Notification event);
    Notification deserialize(String src);
}


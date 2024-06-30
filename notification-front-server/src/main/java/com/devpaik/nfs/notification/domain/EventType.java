package com.devpaik.nfs.notification.domain;

import java.time.LocalDateTime;

public enum EventType {
    NOW, SCHEDULE;


    public static EventType parseEventType(LocalDateTime deliveryReqDtm) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(deliveryReqDtm)) {
            return SCHEDULE;
        }
        return NOW;
    }
}

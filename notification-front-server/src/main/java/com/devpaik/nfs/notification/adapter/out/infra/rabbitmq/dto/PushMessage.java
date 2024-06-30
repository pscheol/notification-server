package com.devpaik.nfs.notification.adapter.out.infra.rabbitmq.dto;


import com.devpaik.nfs.notification.domain.Notification;

import java.time.LocalDateTime;

public record PushMessage(
        Long eventId,
        String receiver,
        String title,
        String contents,
        LocalDateTime deliveryReqDtm,
        String client
) {
    public static PushMessage of(
            Long eventId, Notification notification) {
        return new PushMessage(eventId,
                notification.getReceiver().getValue(),
                notification.getMessage().getTitle().getValue(),
                notification.getMessage().getContents().getValue(),
                notification.getDeliveryReqDtm().getDatetime(),
                notification.getClient().getValue()
        );
    }
}

package com.devpaik.nfs.notification.adapter.out.infra.rabbitmq.dto;

import com.devpaik.nfs.notification.domain.Notification;

import java.time.LocalDateTime;

public record SMSMessage(
        Long eventId,
        String senderNumber,
        String receiverNumber,
        String title,
        String contents,
        LocalDateTime deliveryReqDtm
) {
    public static SMSMessage of(
            Long eventId, Notification notification) {
        return new SMSMessage(eventId,
                notification.getSender().getValue(),
                notification.getReceiver().getValue(),
                notification.getMessage().getTitle().getValue(),
                notification.getMessage().getContents().getValue(),
                notification.getDeliveryReqDtm().getDatetime()
        );
    }
}

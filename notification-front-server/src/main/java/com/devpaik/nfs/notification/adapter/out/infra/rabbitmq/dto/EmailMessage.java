package com.devpaik.nfs.notification.adapter.out.infra.rabbitmq.dto;

import com.devpaik.nfs.notification.domain.Notification;

import java.time.LocalDateTime;

public record EmailMessage(
        Long eventId,
        String senderEmail,
        String receiverEmail,
        String title,
        String contents,
        LocalDateTime deliveryReqDtm
) {
    public static EmailMessage of(
            Long eventId, Notification notification) {
        return new EmailMessage(eventId,
                notification.getSender().getValue(),
                notification.getReceiver().getValue(),
                notification.getMessage().getTitle().getValue(),
                notification.getMessage().getContents().getValue(),
                notification.getDeliveryReqDtm().getDatetime()
        );
    }
}

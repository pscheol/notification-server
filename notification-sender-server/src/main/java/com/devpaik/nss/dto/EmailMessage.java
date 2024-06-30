package com.devpaik.nss.dto;

import java.time.LocalDateTime;

public record EmailMessage(
        Long eventId,
        String senderEmail,
        String receiverEmail,
        String title,
        String contents,
        LocalDateTime deliveryReqDtm
) {
}

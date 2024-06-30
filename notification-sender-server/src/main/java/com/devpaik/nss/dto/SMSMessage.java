package com.devpaik.nss.dto;

import java.time.LocalDateTime;

public record SMSMessage(
        Long eventId,
        String senderNumber,
        String receiverNumber,
        String title,
        String contents,
        LocalDateTime deliveryReqDtm
) {
}

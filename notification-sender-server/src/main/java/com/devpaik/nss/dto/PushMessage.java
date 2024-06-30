package com.devpaik.nss.dto;


import java.time.LocalDateTime;

public record PushMessage(
        Long eventId,
        String receiver,
        String title,
        String contents,
        LocalDateTime deliveryReqDtm,
        String client
) {

}

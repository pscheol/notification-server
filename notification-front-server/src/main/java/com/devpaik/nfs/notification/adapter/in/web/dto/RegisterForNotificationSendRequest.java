package com.devpaik.nfs.notification.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record RegisterForNotificationSendRequest(

        @NotBlank String sender,

        @NotBlank String receiver,

        @NotBlank String title,

        @NotBlank String contents,

        @NotBlank String deliveryReqDtm,

        @NotBlank String client) {

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    public LocalDateTime parseDeliveryReqDtm() {
        return LocalDateTime.parse(deliveryReqDtm, format);
    }

}

package com.devpaik.nss.application.event;

import com.devpaik.nss.dto.EmailMessage;

public record SendEmailEvent(EmailMessage message) {
}

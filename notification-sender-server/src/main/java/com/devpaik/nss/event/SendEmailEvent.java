package com.devpaik.nss.event;

import com.devpaik.nss.dto.EmailMessage;

public record SendEmailEvent(EmailMessage message) {
}

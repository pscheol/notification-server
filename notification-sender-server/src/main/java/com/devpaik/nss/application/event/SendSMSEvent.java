package com.devpaik.nss.application.event;

import com.devpaik.nss.dto.SMSMessage;

public record SendSMSEvent(SMSMessage message) {
}

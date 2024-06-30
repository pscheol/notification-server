package com.devpaik.nss.event;

import com.devpaik.nss.dto.SMSMessage;

public record SendSMSEvent(SMSMessage message) {
}

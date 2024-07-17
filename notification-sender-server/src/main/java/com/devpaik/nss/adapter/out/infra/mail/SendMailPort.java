package com.devpaik.nss.adapter.out.infra.mail;

import com.devpaik.nss.dto.EmailMessage;

public interface SendMailPort {
    public String sendMail(EmailMessage message);
}

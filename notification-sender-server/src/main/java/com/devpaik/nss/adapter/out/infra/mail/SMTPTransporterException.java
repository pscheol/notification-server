package com.devpaik.nss.adapter.out.infra.mail;

public class SMTPTransporterException extends RuntimeException {
    public SMTPTransporterException(String msg) {
        super(msg);
    }
}

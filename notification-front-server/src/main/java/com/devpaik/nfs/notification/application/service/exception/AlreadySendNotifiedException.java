package com.devpaik.nfs.notification.application.service.exception;

public class AlreadySendNotifiedException extends RuntimeException {
    public AlreadySendNotifiedException() {
        super("Already Send Notified.");
    }
}

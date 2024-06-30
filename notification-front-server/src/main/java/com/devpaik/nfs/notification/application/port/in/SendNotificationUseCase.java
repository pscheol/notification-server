package com.devpaik.nfs.notification.application.port.in;

import com.devpaik.nfs.notification.application.port.in.command.SendNotificationCommand;

import java.util.concurrent.ExecutionException;

public interface SendNotificationUseCase {
    void sendNotification(SendNotificationCommand command) throws ExecutionException, InterruptedException;
}

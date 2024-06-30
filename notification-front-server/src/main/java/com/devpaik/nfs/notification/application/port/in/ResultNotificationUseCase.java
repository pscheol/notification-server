package com.devpaik.nfs.notification.application.port.in;

import com.devpaik.nfs.notification.application.port.in.command.SendNotificationCommand;

import java.util.concurrent.ExecutionException;

public interface ResultNotificationUseCase {
    void responseNotificationBySuccess(Long eventId);
    void responseNotificationByFail(Long eventId);
}

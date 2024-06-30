package com.devpaik.nfs.notification.application.service;

import com.devpaik.nfs.notification.application.port.out.EventNotificationPort;
import com.devpaik.nfs.notification.application.port.in.RegisterForEmailSendUseCase;
import com.devpaik.nfs.notification.application.port.in.RegisterForPushSendUseCase;
import com.devpaik.nfs.notification.application.port.in.RegisterForSMSSendUseCase;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForEmailSendCommand;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForPushSendCommand;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForSMSSendCommand;
import com.devpaik.nfs.notification.application.port.out.NotificationEventPort;
import com.devpaik.nfs.notification.domain.event.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class RegisterForNotificationService implements RegisterForPushSendUseCase, RegisterForEmailSendUseCase,
        RegisterForSMSSendUseCase {

    private final NotificationEventPort notificationEventPort;
    private final EventNotificationPort eventStore;

    public RegisterForNotificationService(NotificationEventPort notificationEventPort, EventNotificationPort eventStore) {
        this.notificationEventPort = notificationEventPort;
        this.eventStore = eventStore;
    }

    @Override
    public void registerForPushSend(RegisterForPushSendCommand command) {
        startNotificationEvent(NotificationEvent.createNotificationEventByCommand(command));
    }


    @Override
    public void registerForEmailSend(RegisterForEmailSendCommand command) {
        startNotificationEvent(NotificationEvent.createNotificationEventByCommand(command));
    }

    @Override
    public void registerForSMSSend(RegisterForSMSSendCommand command) {
        startNotificationEvent(NotificationEvent.createNotificationEventByCommand(command));
    }

    private void startNotificationEvent(NotificationEvent notificationEvent) {
        NotificationEvent createNotificationEvent = eventStore.save(notificationEvent);
        notificationEventPort.publishNotificationEvent(createNotificationEvent.startNotificationSendEvent());
    }
}

package com.devpaik.nfs.notification.application.service;

import com.devpaik.nfs.notification.application.port.in.ResultNotificationUseCase;
import com.devpaik.nfs.notification.application.port.out.EventNotificationPort;
import com.devpaik.nfs.notification.application.port.in.SendNotificationUseCase;
import com.devpaik.nfs.notification.application.port.in.command.SendNotificationCommand;
import com.devpaik.nfs.notification.application.port.out.NotificationEventPort;
import com.devpaik.nfs.notification.application.port.out.SendNotificationClientPort;
import com.devpaik.nfs.notification.application.service.exception.AlreadySendNotifiedException;
import com.devpaik.nfs.notification.domain.Channel;
import com.devpaik.nfs.notification.domain.event.FailNotificationEvent;
import com.devpaik.nfs.notification.domain.event.NotificationEvent;
import com.devpaik.nfs.notification.domain.event.SuccessNotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Transactional
@Slf4j
@Service
public class SendNotificationService implements SendNotificationUseCase, ResultNotificationUseCase {

    private final SendNotificationClientPort sendNotificationClientPort;
    private final EventNotificationPort eventStore;
    private final NotificationEventPort notificationEventPort;


    public SendNotificationService(SendNotificationClientPort sendNotificationClientPort, EventNotificationPort eventStore, NotificationEventPort notificationEventPort) {
        this.sendNotificationClientPort = sendNotificationClientPort;
        this.eventStore = eventStore;
        this.notificationEventPort = notificationEventPort;
    }

    @Override
    public void sendNotification(SendNotificationCommand command) throws ExecutionException, InterruptedException {

        NotificationEvent event = getNotificationEvent(command.eventId().getId());

        eventStore.updateState(event.changeSendState());

        switch (command.channel()) {
            case SMS -> sendSMS(event);
            case EMAIL -> sendEmail(event);
            case PUSH -> sendPush(event);
        };
    }

    @Override
    public void responseNotificationBySuccess(Long eventId) {
        NotificationEvent event = getNotificationEvent(eventId);
        notificationEventPort.publishNotificationEvent(new SuccessNotificationEvent(event));
    }

    @Override
    public void responseNotificationByFail(Long eventId) {
        NotificationEvent event = getNotificationEvent(eventId);
        notificationEventPort.publishNotificationEvent(new FailNotificationEvent(event));

    }

    private void sendEmail(NotificationEvent event) {
        sendNotificationClientPort.sendNotificationEmailClient(event.getEventId(), event.getEventData());
    }

    private void sendPush(NotificationEvent event) {
        sendNotificationClientPort.sendNotificationPushClient(event.getEventId(), event.getEventData());
    }

    private void sendSMS(NotificationEvent event) {
        sendNotificationClientPort.sendNotificationSMSClient(event.getEventId(), event.getEventData());
    }

    private NotificationEvent getNotificationEvent(Long eventId) {
        NotificationEvent event = eventStore.findById(eventId);
        if (event.getState().checkSuccess()) {
            throw new AlreadySendNotifiedException();
        }
        return event;
    }
}

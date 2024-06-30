package com.devpaik.nfs.notification.adapter.out.handler;

import com.devpaik.nfs.notification.application.port.out.EventNotificationPort;
import com.devpaik.nfs.notification.application.port.in.SendNotificationUseCase;
import com.devpaik.nfs.notification.application.port.in.command.SendNotificationCommand;
import com.devpaik.nfs.notification.application.port.out.InsertNotificationPort;
import com.devpaik.nfs.notification.domain.event.FailNotificationEvent;
import com.devpaik.nfs.notification.domain.event.SendNotificationEvent;
import com.devpaik.nfs.notification.domain.event.SuccessNotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class SendNotificationHandler {

    private final InsertNotificationPort insertNotificationPort;
    private final SendNotificationUseCase sendNotificationUseCase;
    private final EventNotificationPort eventStore;

    public SendNotificationHandler(InsertNotificationPort insertNotificationPort, SendNotificationUseCase sendNotificationUseCase, EventNotificationPort eventStore) {
        this.insertNotificationPort = insertNotificationPort;
        this.sendNotificationUseCase = sendNotificationUseCase;
        this.eventStore = eventStore;
    }

    @Async("notificationSendEventExecutor")
    @TransactionalEventListener(classes = SendNotificationEvent.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendNotificationEventHandler(SendNotificationEvent event) throws ExecutionException, InterruptedException {
        log.debug("@@ sendNotificationEventHandler={}", event);


        SendNotificationCommand command = SendNotificationCommand.of(
                event.eventId(),
                event.receiver(),
                event.message().getTitle(),
                event.message().getContents(),
                event.channel());

        sendNotificationUseCase.sendNotification(command);
    }


    @EventListener(classes = SuccessNotificationEvent.class)
    public void successNotificationEventHandler(SuccessNotificationEvent event) {
        log.debug("successNotificationEventHandler={}", event);

        eventStore.updateState(event.notificationEvent().changeSuccessState());

        insertNotificationPort.saveNotification(event.notificationEvent().getEventData());
    }


    @EventListener(classes = FailNotificationEvent.class)
    public void failNotificationEventHandler(FailNotificationEvent event) {
        log.debug("failNotificationEventHandler={}", event);

        eventStore.updateState(event.notificationEvent().changeFailState());
    }
}

package com.devpaik.nfs.notification.application.service;

import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendRequest;
import com.devpaik.nfs.notification.application.port.out.EventNotificationPort;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForSMSSendCommand;
import com.devpaik.nfs.notification.application.port.in.command.SendNotificationCommand;
import com.devpaik.nfs.notification.application.port.out.NotificationEventPort;
import com.devpaik.nfs.notification.application.port.out.SendNotificationClientPort;
import com.devpaik.nfs.notification.domain.*;
import com.devpaik.nfs.notification.domain.event.NotificationEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.ExecutionException;

@ExtendWith(SpringExtension.class)
class SendNotificationServiceTest {

    private final SendNotificationClientPort sendNotificationClientPort =
            Mockito.mock(SendNotificationClientPort.class);

    private final NotificationEventPort notificationEventPort =
            Mockito.mock(NotificationEventPort.class);

    private final EventNotificationPort eventStore =
            Mockito.mock(EventNotificationPort.class);

//    private final SendNotificationService sendNotificationService =
//            new SendNotificationService(sendNotificationClientPort, notificationEventPort, eventStore);


    @DisplayName("알림 전송 테스트")
    @Test
    void sendNotificationTest() throws ExecutionException, InterruptedException {
        RegisterForNotificationSendRequest request = new RegisterForNotificationSendRequest
                ("15555555", "01012341234", "안녕하세요", "테스트입니다.",
                        "202405111120", "web");
        RegisterForSMSSendCommand registerCommand = RegisterForSMSSendCommand.of(request);

        NotificationEvent notificationEvent = NotificationEvent.createNotificationEventByCommand(registerCommand);

        BDDMockito.given(eventStore.findById(1L)).willReturn(notificationEvent);
//        BDDMockito.given(sendNotificationClientPort.sendNotificationSMSClient("01012341234", "안녕하세요.", "테스트입니다."))
//                .willReturn("FAIL");

        SendNotificationCommand sendCommand = SendNotificationCommand
                .of(new EventId(1L), Receiver.of("01012341234"),
                        Title.of("안녕하세요"), Contents.of("테스트입니다."), Channel.SMS);

//        sendNotificationService.sendNotification(sendCommand);
    }
}
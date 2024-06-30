package com.devpaik.nfs.notification.application.service;

import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendRequest;
import com.devpaik.nfs.notification.application.port.out.EventNotificationPort;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForEmailSendCommand;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForPushSendCommand;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForSMSSendCommand;
import com.devpaik.nfs.notification.application.port.out.NotificationEventPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RegisterForNotificationServiceTest {

    private final NotificationEventPort notificationEventPort =
            Mockito.mock(NotificationEventPort.class);

    private final EventNotificationPort eventStore =
            Mockito.mock(EventNotificationPort.class);

    private final RegisterForNotificationService registerForNotificationService =
            new RegisterForNotificationService(notificationEventPort, eventStore);


    @DisplayName("알림 이메일 등록 테스트")
//    @Test
    void registerForEmailSendTest() {
        RegisterForNotificationSendRequest request = new RegisterForNotificationSendRequest
                ("test@gmail.com", "pscheol@gmail.cpm", "안녕하세요", "테스트입니다.",
                        "202405111120", "web");

        RegisterForEmailSendCommand registerCommand = RegisterForEmailSendCommand.of(request);

        registerForNotificationService.registerForEmailSend(registerCommand);
    }

    @DisplayName("알림 SMS 등록 테스트")
//    @Test
    void registerForSMSSendTest() {
        RegisterForNotificationSendRequest request = new RegisterForNotificationSendRequest
                ("test@gmail.com", "pscheol@gmail.cpm", "안녕하세요", "테스트입니다.",
                        "202405111120", "web");

        RegisterForSMSSendCommand registerCommand = RegisterForSMSSendCommand.of(request);

        registerForNotificationService.registerForSMSSend(registerCommand);
    }

    @DisplayName("알림 Push 등록 테스트")
//    @Test
    void registerForPushSendTest() {
        RegisterForNotificationSendRequest request = new RegisterForNotificationSendRequest
                ("test@gmail.com", "pscheol@gmail.cpm", "안녕하세요", "테스트입니다.",
                        "202405111120", "web");

        RegisterForPushSendCommand registerCommand = RegisterForPushSendCommand.of(request);

        registerForNotificationService.registerForPushSend(registerCommand);
    }
}
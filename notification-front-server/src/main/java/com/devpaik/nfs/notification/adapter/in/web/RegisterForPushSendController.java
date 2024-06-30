package com.devpaik.nfs.notification.adapter.in.web;

import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendRequest;
import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendResponse;
import com.devpaik.nfs.notification.application.port.in.RegisterForPushSendUseCase;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForPushSendCommand;
import com.devpaik.nfs.notification.exceptions.ServiceCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "카카오톡 알림 전송 요청 Controller")
@Slf4j
@RestController
@RequestMapping("/api/v1/notifications/send")
public class RegisterForPushSendController {

    private final RegisterForPushSendUseCase registerForPushSendUseCase;

    public RegisterForPushSendController(RegisterForPushSendUseCase registerForPushSendUseCase) {
        this.registerForPushSendUseCase = registerForPushSendUseCase;
    }

    @Operation(summary = "Push 알림 전송 요청")
    @PostMapping("/push")
    public RegisterForNotificationSendResponse sendPushNotification(@RequestBody RegisterForNotificationSendRequest registerForNotificationSendRequest) {
        log.debug("@@ sendPushNotification registerForNotificationSendRequest={}", registerForNotificationSendRequest);

        RegisterForPushSendCommand command = RegisterForPushSendCommand.of(registerForNotificationSendRequest);

        registerForPushSendUseCase.registerForPushSend(command);

        return RegisterForNotificationSendResponse.build(ServiceCode.OK);
    }
}

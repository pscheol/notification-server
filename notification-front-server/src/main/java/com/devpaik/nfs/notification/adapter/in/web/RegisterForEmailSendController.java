package com.devpaik.nfs.notification.adapter.in.web;

import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendRequest;
import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendResponse;
import com.devpaik.nfs.notification.application.port.in.RegisterForEmailSendUseCase;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForEmailSendCommand;
import com.devpaik.nfs.notification.exceptions.ServiceCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "이메일 알림 전송 요청 Controller")
@Slf4j
@RestController
@RequestMapping("/api/v1/notifications/send")
public class RegisterForEmailSendController {

    private final RegisterForEmailSendUseCase registerForEmailSendUseCase;

    public RegisterForEmailSendController(RegisterForEmailSendUseCase registerForEmailSendUseCase) {
        this.registerForEmailSendUseCase = registerForEmailSendUseCase;
    }

    @Operation(summary = "이메일 알림 전송 요청")
    @PostMapping("/email")
    public RegisterForNotificationSendResponse registerForEmailSend(@RequestBody @Valid RegisterForNotificationSendRequest registerForNotificationSendRequest) {
        log.debug("@@ registerForEmailSend registerForNotificationSendRequest={}", registerForNotificationSendRequest);

        RegisterForEmailSendCommand command = RegisterForEmailSendCommand.of(registerForNotificationSendRequest);

        registerForEmailSendUseCase.registerForEmailSend(command);

        return RegisterForNotificationSendResponse.build(ServiceCode.OK);
    }
}

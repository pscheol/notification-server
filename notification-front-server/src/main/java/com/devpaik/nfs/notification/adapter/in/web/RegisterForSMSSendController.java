package com.devpaik.nfs.notification.adapter.in.web;

import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendRequest;
import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendResponse;
import com.devpaik.nfs.notification.application.port.in.RegisterForSMSSendUseCase;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForSMSSendCommand;
import com.devpaik.nfs.notification.exceptions.ServiceCode;
import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendRequest;
import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendResponse;
import com.devpaik.nfs.notification.application.port.in.RegisterForSMSSendUseCase;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForSMSSendCommand;
import com.devpaik.nfs.notification.exceptions.ServiceCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SMS 알림 전송 요청 Controller")
@Slf4j
@RestController
@RequestMapping("/api/v1/notifications/send")
public class RegisterForSMSSendController {

    private final RegisterForSMSSendUseCase registerForSMSSendUseCase;

    public RegisterForSMSSendController(RegisterForSMSSendUseCase registerForSMSSendUseCase) {
        this.registerForSMSSendUseCase = registerForSMSSendUseCase;
    }

    @Operation(summary = "SMS 알림 전송 요청")
    @PostMapping("/sms")
    public RegisterForNotificationSendResponse sendSMSNotification(@RequestBody RegisterForNotificationSendRequest registerForNotificationSendRequest) {
        log.debug("@@ sendSMSNotification registerForNotificationSendRequest={}", registerForNotificationSendRequest);

        RegisterForSMSSendCommand command = RegisterForSMSSendCommand.of(registerForNotificationSendRequest);

        registerForSMSSendUseCase.registerForSMSSend(command);

        return RegisterForNotificationSendResponse.build(ServiceCode.OK);
    }
}

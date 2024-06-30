package com.devpaik.nfs.notification.application.port.in;

import com.devpaik.nfs.notification.application.port.in.command.RegisterForEmailSendCommand;

public interface RegisterForEmailSendUseCase {
    void registerForEmailSend(RegisterForEmailSendCommand command);
}

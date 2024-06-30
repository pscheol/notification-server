package com.devpaik.nfs.notification.application.port.in;

import com.devpaik.nfs.notification.application.port.in.command.RegisterForPushSendCommand;

public interface RegisterForPushSendUseCase {
    void registerForPushSend(RegisterForPushSendCommand command);
}

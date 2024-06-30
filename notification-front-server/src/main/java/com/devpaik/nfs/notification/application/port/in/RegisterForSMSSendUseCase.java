package com.devpaik.nfs.notification.application.port.in;

import com.devpaik.nfs.notification.application.port.in.command.RegisterForSMSSendCommand;

public interface RegisterForSMSSendUseCase {
    void registerForSMSSend(RegisterForSMSSendCommand command);
}

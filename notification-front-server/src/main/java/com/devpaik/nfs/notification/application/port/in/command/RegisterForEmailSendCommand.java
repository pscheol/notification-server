package com.devpaik.nfs.notification.application.port.in.command;


import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendRequest;
import com.devpaik.nfs.notification.domain.*;

public class RegisterForEmailSendCommand extends RegisterForNotificationSendCommand {

    private RegisterForEmailSendCommand(Channel channel, Sender sender, Receiver receiver,
                                        Message message, DeliveryReqDtm deliveryReqDtm, Client client) {
        super(channel, sender, receiver, message, deliveryReqDtm, client);
    }

    public static RegisterForEmailSendCommand of(RegisterForNotificationSendRequest request) {
        return new RegisterForEmailSendCommand(
                Channel.EMAIL,
                Sender.of(request.sender()),
                Receiver.of(request.receiver()),
                Message.of(request.title(), request.contents()),
                DeliveryReqDtm.of(request.parseDeliveryReqDtm()),
                Client.of(request.client())
        );
    }
}

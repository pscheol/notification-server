package com.devpaik.nfs.notification.application.port.in.command;


import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendRequest;
import com.devpaik.nfs.notification.domain.*;

public class RegisterForSMSSendCommand extends RegisterForNotificationSendCommand {

    public RegisterForSMSSendCommand(Channel channel, Sender sender, Receiver receiver, Message message, DeliveryReqDtm deliveryReqDtm, Client client) {
        super(channel, sender, receiver, message, deliveryReqDtm, client);
    }

    public static RegisterForSMSSendCommand of(RegisterForNotificationSendRequest request) {

        return new RegisterForSMSSendCommand(
                Channel.SMS,
                Sender.of(request.sender()),
                Receiver.of(request.receiver()),
                Message.of(request.title(), request.contents()),
                DeliveryReqDtm.of(request.parseDeliveryReqDtm()),
                Client.of(request.client())
        );
    }
}

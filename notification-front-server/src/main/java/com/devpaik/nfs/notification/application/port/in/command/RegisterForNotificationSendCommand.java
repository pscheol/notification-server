package com.devpaik.nfs.notification.application.port.in.command;


import com.devpaik.nfs.notification.domain.*;
import lombok.Getter;

public abstract class RegisterForNotificationSendCommand {

    @Getter
    private Channel channel;

    @Getter
    private Sender sender;

    @Getter
    private Receiver receiver;

    @Getter
    private Message message;

    @Getter
    private DeliveryReqDtm deliveryReqDtm;

    @Getter
    private Client client;

    protected RegisterForNotificationSendCommand(Channel channel, Sender sender, Receiver receiver,
                                                 Message message, DeliveryReqDtm deliveryReqDtm, Client client) {
        this.channel = channel;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.deliveryReqDtm = deliveryReqDtm;
        this.client = client;
    }
}

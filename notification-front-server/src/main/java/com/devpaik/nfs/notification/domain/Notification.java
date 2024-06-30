package com.devpaik.nfs.notification.domain;

import com.devpaik.nfs.notification.adapter.out.persistence.notification.entity.NotificationEntity;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForNotificationSendCommand;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

public class Notification implements Serializable {

    @Getter
    private final NotificationId id;

    @Getter
    private final Sender sender;

    @Getter
    private final Receiver receiver;

    @Getter
    private final Channel channel;

    @Getter
    private final Message message;

    @Getter
    private final DeliveryReqDtm deliveryReqDtm;

    @Getter
    private final Client client;

    @Getter
    private final CreateDt createDt;



    private Notification() {
        this(null, null, null, null, null, null, null, null);
    }



    public Notification(NotificationId id, Sender sender, Receiver receiver, Channel channel,
                        Message message, DeliveryReqDtm deliveryReqDtm, Client client, CreateDt createDt) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.channel = channel;
        this.message = message;
        this.deliveryReqDtm = deliveryReqDtm;
        this.client = client;
        this.createDt = createDt;
    }

    public static Notification createNotificationByCommand(RegisterForNotificationSendCommand command) {
        return new Notification(
                null,
                command.getSender(),
                command.getReceiver(),
                command.getChannel(),
                command.getMessage(),
                command.getDeliveryReqDtm(),
                command.getClient(),
                null
            );
    }

    public static Notification createNotificationByEntity(NotificationEntity notificationEntity) {
        return new Notification(
                new NotificationId(notificationEntity.getNotificationId()),
                Sender.of(notificationEntity.getSender()),
                Receiver.of(notificationEntity.getReceiver()),
                Channel.valueOf(notificationEntity.getChannel().toString()),
                Message.of(notificationEntity.getTitle(), notificationEntity.getContents()),
                DeliveryReqDtm.of(notificationEntity.getDeliveryReqDtm()),
                Client.of(notificationEntity.getClient()),
                new CreateDt(notificationEntity.getCreateDt())
        );
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", channel=" + channel +
                ", message=" + message +
                ", deliveryReqDtm=" + deliveryReqDtm +
                ", client=" + client +
                ", createDt=" + createDt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id) && Objects.equals(sender, that.sender) &&
                Objects.equals(receiver, that.receiver) && Objects.equals(channel, that.channel) &&
                Objects.equals(message, that.message) && Objects.equals(deliveryReqDtm, that.deliveryReqDtm) &&
                Objects.equals(client, that.client) && Objects.equals(createDt, that.createDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, receiver, channel, message, deliveryReqDtm, client, createDt);
    }
}

package com.devpaik.nfs.notification.domain.event;

import com.devpaik.nfs.notification.domain.EventType;
import com.devpaik.nfs.notification.domain.Notification;
import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.NotificationEventEntity;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForNotificationSendCommand;
import com.devpaik.nfs.notification.domain.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

public class NotificationEvent implements Serializable {

    @Getter
    private final EventId eventId;

    @Getter
    private final Version version;

    @Getter
    private final State state;

    @Getter
    private final EventType eventType;

    @Getter
    private final Notification eventData;

    @Getter
    private final DeliveryReqDtm deliveryReqDtm;

    @Getter
    private final CreateDt createDt;


    private NotificationEvent(EventId eventId, Version version, State state,
                             EventType eventType, Notification eventData,
                             DeliveryReqDtm deliveryReqDtm,
                             CreateDt createDt) {
        this.eventId = eventId;
        this.version = version;
        this.state = state;
        this.eventType = eventType;
        this.eventData = eventData;
        this.deliveryReqDtm = deliveryReqDtm;
        this.createDt = createDt;
    }

    public static NotificationEvent createNotificationEventByCommand(RegisterForNotificationSendCommand command) {
        return new NotificationEvent(
                null,
                 null,
                State.REQ,
                EventType.parseEventType(command.getDeliveryReqDtm().getDatetime()),
                Notification.createNotificationByCommand(command),
                command.getDeliveryReqDtm(),
                null
        );
    }

    public static NotificationEvent createNotificationEventByEntity(NotificationEventEntity entity, Notification notification) {
        return new NotificationEvent(
                new EventId(entity.getEventId()),
                new Version(entity.getVersion()),
                State.valueOf(entity.getState().toString()),
                EventType.valueOf(entity.getEventType().toString()),
                notification,
                DeliveryReqDtm.of(entity.getDeliveryReqDtm()),
                new CreateDt(entity.getCreateDt())
        );
    }
    public StartNotificationSendEvent startNotificationSendEvent() {
        return new StartNotificationSendEvent(this);
    }


    @Override
    public String toString() {
        return "NotificationEvent{" +
                "eventId=" + eventId +
                ", version=" + version +
                ", state=" + state +
                ", eventType=" + eventType +
                ", eventData=" + eventData +
                ", deliveryReqDtm=" + deliveryReqDtm +
                ", createDt=" + createDt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationEvent event = (NotificationEvent) o;
        return Objects.equals(eventId, event.eventId) && Objects.equals(version, event.version) &&
                Objects.equals(state, event.state) && eventType == event.eventType &&
                Objects.equals(eventData, event.eventData) && Objects.equals(deliveryReqDtm, event.deliveryReqDtm) &&
                Objects.equals(createDt, event.createDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, version, state, eventType, eventData, deliveryReqDtm, createDt);
    }

    public NotificationEvent changeSuccessState() {
        return new NotificationEvent(
                this.getEventId(),
                this.getVersion(),
                State.SUCCESS,
                this.getEventType(),
                this.getEventData(),
                this.getDeliveryReqDtm(),
                this.getCreateDt()
        );
    }

    public NotificationEvent changeFailState() {
        return new NotificationEvent(
                this.getEventId(),
                this.getVersion(),
                State.FAIL,
                this.getEventType(),
                this.getEventData(),
                this.getDeliveryReqDtm(),
                this.getCreateDt()
        );
    }

    public NotificationEvent changeSendState() {
        return new NotificationEvent(
                this.getEventId(),
                this.getVersion(),
                State.SEND,
                this.getEventType(),
                this.getEventData(),
                this.getDeliveryReqDtm(),
                this.getCreateDt()
        );
    }
}

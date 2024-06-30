package com.devpaik.nfs.notification.adapter.out.persistence.event;

import com.devpaik.nfs.common.Serializer;
import com.devpaik.nfs.notification.adapter.out.infra.event.EventSerializer;
import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.EventType;
import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.NotificationEventEntity;
import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.State;
import com.devpaik.nfs.notification.domain.Notification;
import com.devpaik.nfs.notification.domain.event.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
class NotificationEventMapper implements EventSerializer {

    private final Serializer serializer;

    public NotificationEventMapper(Serializer serializer) {
        this.serializer = serializer;
    }

    NotificationEvent mpaToDomain(NotificationEventEntity entity) {
        return NotificationEvent.createNotificationEventByEntity(entity, deserialize(entity.getEventData()));
    }

    NotificationEventEntity mapToEntity(NotificationEvent notificationEvent) {
        return NotificationEventEntity.builder()
                .eventId(notificationEvent.getEventId() == null ? null : notificationEvent.getEventId().getId())
                .state(State.valueOf(notificationEvent.getState().toString()))
                .version(notificationEvent.getVersion() == null ? null : notificationEvent.getVersion().getValue())
                .eventType(EventType.valueOf(notificationEvent.getEventType().toString()))
                .deliveryReqDtm(notificationEvent.getDeliveryReqDtm().getDatetime())
                .eventData(serialize(notificationEvent.getEventData()))
                .createDt(notificationEvent.getCreateDt() == null ? LocalDateTime.now() : notificationEvent.getCreateDt().getDateTime())
                .build();

    }

    @Override
    public String serialize(Notification event) {
        String result = serializer.serialize(event);
        log.debug("# serialize Notification={}", result);
        return result;
    }

    @Override
    public Notification deserialize(String src) {
        Notification result = serializer.deserialize(src, Notification.class);
        log.debug("# deserialize Notification={}", result);
        return result;

    }
}

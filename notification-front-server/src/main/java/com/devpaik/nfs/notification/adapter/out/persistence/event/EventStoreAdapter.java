package com.devpaik.nfs.notification.adapter.out.persistence.event;

import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.EventType;
import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.NotificationEventEntity;
import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.State;
import com.devpaik.nfs.notification.application.port.out.EventNotificationPort;
import com.devpaik.nfs.notification.domain.event.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EventStoreAdapter implements EventNotificationPort {
    private final NotificationEventRepository notificationEventRepository;
    private final NotificationEventMapper notificationEventMapper;

    public EventStoreAdapter(NotificationEventRepository notificationEventRepository, NotificationEventMapper notificationEventMapper) {
        this.notificationEventRepository = notificationEventRepository;
        this.notificationEventMapper = notificationEventMapper;
    }

    @Override
    public NotificationEvent save(NotificationEvent notificationEvent) {
        NotificationEventEntity entity = notificationEventRepository.save(notificationEventMapper.mapToEntity(notificationEvent));
        return notificationEventMapper.mpaToDomain(entity);
    }

    @Override
    public void updateState(NotificationEvent notificationEvent) {
        notificationEventRepository.save(notificationEventMapper.mapToEntity(notificationEvent));
    }


    @Override
    public List<NotificationEvent> findByAllStateAndEventTypeAndDateTime(State state, EventType eventType, LocalDateTime dateTime, int offset, int limit) {
        PageRequest request = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "deliveryReqDtm"));

        return notificationEventRepository.findByStateAndEventTypeAndContainingDeliveryReqDtmPageable(state, eventType, dateTime, request)
                .orElse(Collections.emptyList())
                .stream().map(notificationEventMapper::mpaToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationEvent> findByAllStateAndDateTime(State state, LocalDateTime dateTime, int offset, int limit) {
        PageRequest request = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "deliveryReqDtm"));

        return notificationEventRepository.findByStateAndContainingDeliveryReqDtmPageable(state, dateTime, request)
                .orElse(Collections.emptyList())
                .stream().map(notificationEventMapper::mpaToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationEvent findById(Long eventId) {
        NotificationEventEntity entity = notificationEventRepository.findByIdWithOptimisticLock(eventId)
                .orElseThrow(NotFoundNotificationEventException::new);
        return notificationEventMapper.mpaToDomain(entity);

    }
}

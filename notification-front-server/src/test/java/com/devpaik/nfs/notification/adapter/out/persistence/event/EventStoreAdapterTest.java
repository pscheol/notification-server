package com.devpaik.nfs.notification.adapter.out.persistence.event;

import com.devpaik.nfs.common.Serializer;
import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendRequest;
import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.EventType;
import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.State;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForSMSSendCommand;
import com.devpaik.nfs.notification.domain.event.NotificationEvent;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

@Sql("classpath:notification_event_insert.sql")
@DataJpaTest
@Import({EventStoreAdapter.class, NotificationEventMapper.class, Serializer.class})
class EventStoreAdapterTest {

    @Autowired
    private Serializer serializer;

    @Autowired
    private EventStoreAdapter eventStoreAdapter;

    @Autowired
    private NotificationEventMapper notificationEventMapper;

    @DisplayName("SMS 알림 이벤트 등록")
    @Test
    void saveSMSTest() {
        RegisterForNotificationSendRequest request = new RegisterForNotificationSendRequest
                ("15555555", "01012341234", "안녕하세요", "테스트입니다.",
                        "202405111120", "web");
        RegisterForSMSSendCommand command = RegisterForSMSSendCommand.of(request);

        NotificationEvent notificationEvent = NotificationEvent.createNotificationEventByCommand(command);

        saveAndGetEqualCheck(notificationEvent);
    }


    @DisplayName("Email 알림 이벤트 등록")
    @Test
    void saveEmailTest() {
        RegisterForNotificationSendRequest request = new RegisterForNotificationSendRequest
                ("test@gmail.com", "pscheol@gmail.com", "안녕하세요", "테스트입니다.",
                        "202406111120", "web");
        RegisterForSMSSendCommand command = RegisterForSMSSendCommand.of(request);

        NotificationEvent notificationEvent = NotificationEvent.createNotificationEventByCommand(command);

        saveAndGetEqualCheck(notificationEvent);
    }


    @DisplayName("Push 알림 이벤트 등록")
    @Test
    void savePushTest() {
        RegisterForNotificationSendRequest request = new RegisterForNotificationSendRequest
                ("test@gmail.com", "pscheol@gmail.com", "안녕하세요", "테스트입니다.",
                        "202405111120", "web");
        RegisterForSMSSendCommand command = RegisterForSMSSendCommand.of(request);

        NotificationEvent notificationEvent = NotificationEvent.createNotificationEventByCommand(command);

        saveAndGetEqualCheck(notificationEvent);
    }

    private void saveAndGetEqualCheck(NotificationEvent notificationEvent) {
        NotificationEvent saveEvent = eventStoreAdapter.save(notificationEvent);

        NotificationEvent getNotificationEvent = eventStoreAdapter.findById(saveEvent.getEventId().getId());

        Assertions.assertThat(saveEvent).isEqualTo(getNotificationEvent);
    }


    @DisplayName("Push 알림 실패상태 변경")
    @Test
    void updateTest() {
        RegisterForNotificationSendRequest request = new RegisterForNotificationSendRequest
                ("test@gmail.com", "pscheol@gmail.com", "안녕하세요", "테스트입니다.",
                        "202405111120", "web");
        RegisterForSMSSendCommand command = RegisterForSMSSendCommand.of(request);

        NotificationEvent notificationEvent = NotificationEvent.createNotificationEventByCommand(command);
        NotificationEvent saveEvent = eventStoreAdapter.save(notificationEvent);

        NotificationEvent failEventState = saveEvent.changeFailState();
        eventStoreAdapter.updateState(failEventState);

        NotificationEvent getNotificationEvent = eventStoreAdapter.findById(saveEvent.getEventId().getId());

        Assertions.assertThat(failEventState.getState()).isEqualTo(getNotificationEvent.getState());
    }
    @DisplayName("상태, 조회 목록 테스트")
    @Test
    void findByAllStateAndDateTimeTest() {
        List<NotificationEvent> result = eventStoreAdapter.findByAllStateAndDateTime(State.REQ, LocalDateTime.now(), 0, 10);

        Assertions.assertThat(result.size()).isEqualTo(1);
    }


    @DisplayName("상태, 이벤트 타입 조회 목록")
    @Test
    void findByAllStateAndEventTypeAndDateTimeTest() {
        List<NotificationEvent> result = eventStoreAdapter.findByAllStateAndEventTypeAndDateTime(State.REQ, EventType.NOW, LocalDateTime.now(), 0, 10);
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    @DisplayName("이벤트 조회")
    @Test
    void findByIdTest() {
        List<NotificationEvent> result = eventStoreAdapter.findByAllStateAndEventTypeAndDateTime(State.REQ, EventType.NOW, LocalDateTime.now(), 0, 10);
        for (NotificationEvent notificationEvent : result) {
            NotificationEvent event = eventStoreAdapter.findById(notificationEvent.getEventId().getId());
            Assertions.assertThat(event).isNotNull();
        }

    }
}
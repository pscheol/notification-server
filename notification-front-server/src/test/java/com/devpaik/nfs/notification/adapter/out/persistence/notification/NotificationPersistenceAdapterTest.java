package com.devpaik.nfs.notification.adapter.out.persistence.notification;

import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendRequest;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForSMSSendCommand;
import com.devpaik.nfs.notification.domain.event.NotificationEvent;

import com.devpaik.nfs.notification.domain.query.NotificationView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@DataJpaTest
@Import({NotificationPersistenceAdapter.class, NotificationMapper.class})
class NotificationPersistenceAdapterTest {

    @Autowired
    private NotificationPersistenceAdapter notificationPersistenceAdapter;

    @Autowired
    private NotificationMapper notificationMapper;



    @DisplayName("알림 테이블 완료 등록 테스트")
    @Test
    void saveNotificationTest() {
        RegisterForNotificationSendRequest request = new RegisterForNotificationSendRequest
                ("15555555", "01012341234", "안녕하세요", "테스트입니다.",
                        "202405111120", "web");
        RegisterForSMSSendCommand command = RegisterForSMSSendCommand.of(request);

        NotificationEvent notificationEvent = NotificationEvent.createNotificationEventByCommand(command);

        notificationPersistenceAdapter.saveNotification(notificationEvent.getEventData());

        Assertions.assertThat(notificationPersistenceAdapter.totalNotificationSuccess()).isEqualTo(1);
    }

    @Sql("classpath:notification_insert.sql")
    @DisplayName("알림 테이블 완료 데이터 3개월 지난 목록 삭제 테스트")
    @Test
    void findByAllStateAndEventTypeAndDateTimeTest() {
        LocalDateTime endDtm = LocalDateTime.of(LocalDate.now().minusMonths(3), LocalTime.of(23, 59 ,59));

        notificationPersistenceAdapter.deleteNotification3Month(endDtm);

        List<NotificationView> result = notificationPersistenceAdapter.selectNotificationSuccessList(1, 10);

        Assertions.assertThat(result.size()).isEqualTo(1);
    }


    @Sql("classpath:notification_insert.sql")
    @DisplayName("알림 테이블 완료 데이터 목록 테스트")
    @Test
    void selectNotificationSuccessList() {
        List<NotificationView> result = notificationPersistenceAdapter.selectNotificationSuccessList(1, 10);

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
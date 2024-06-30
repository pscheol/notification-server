package com.devpaik.nfs.notification.adapter.out.infra.schedule;

import com.devpaik.nfs.notification.application.port.out.UpdateNotificationStatePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@Component
public class DeleteNotificationSchedule {
    private final UpdateNotificationStatePort updateNotificationStatePort;

    public DeleteNotificationSchedule(UpdateNotificationStatePort updateNotificationStatePort) {
        this.updateNotificationStatePort = updateNotificationStatePort;
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void delete3MonthNotification() {
        LocalDateTime endDtm = LocalDateTime.of(LocalDate.now().minusMonths(3), LocalTime.of(23, 59 ,59));

        updateNotificationStatePort.deleteNotification3Month(endDtm);
    }
}

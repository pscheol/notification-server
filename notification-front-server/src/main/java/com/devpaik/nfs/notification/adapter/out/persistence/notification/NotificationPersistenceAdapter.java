package com.devpaik.nfs.notification.adapter.out.persistence.notification;


import com.devpaik.nfs.notification.adapter.out.persistence.notification.repository.NotificationRepository;
import com.devpaik.nfs.notification.application.port.out.GetNotificationQueryPort;
import com.devpaik.nfs.notification.application.port.out.InsertNotificationPort;
import com.devpaik.nfs.notification.application.port.out.UpdateNotificationStatePort;
import com.devpaik.nfs.notification.domain.Notification;
import com.devpaik.nfs.notification.domain.query.NotificationView;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationPersistenceAdapter implements InsertNotificationPort,
        UpdateNotificationStatePort, GetNotificationQueryPort {
    private final NotificationMapper notificationMapper;
    private final NotificationRepository notificationRepository;

    public NotificationPersistenceAdapter(NotificationMapper notificationMapper, NotificationRepository notificationRepository) {
        this.notificationMapper = notificationMapper;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void saveNotification(Notification notification) {
        notificationRepository.save(notificationMapper.mapToEntity(notification));
    }

    @Override
    public void deleteNotification3Month(LocalDateTime endDtm) {
        notificationRepository.deleteBy3MonthDeliveryReqDtm(endDtm);
    }

    @Override
    public List<NotificationView> selectNotificationSuccessList(int page, int limit) {
        PageRequest request = PageRequest.of(page - 1, Math.max(limit, 1), Sort.by(Sort.Direction.DESC, "createDt"));
        return notificationRepository.findAll(request).getContent()
                .stream()
                .map(notificationMapper::mapToQuertView)
                .collect(Collectors.toList());
    }

    @Override
    public long totalNotificationSuccess() {
        return notificationRepository.count();
    }
}

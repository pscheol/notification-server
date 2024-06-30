package com.devpaik.nfs.notification.adapter.out.persistence.notification;

import com.devpaik.nfs.notification.adapter.out.persistence.notification.entity.Channel;
import com.devpaik.nfs.notification.adapter.out.persistence.notification.entity.NotificationEntity;
import com.devpaik.nfs.notification.domain.Notification;
import com.devpaik.nfs.notification.domain.query.NotificationView;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
class NotificationMapper {

    NotificationEntity mapToEntity(Notification notification) {
        return NotificationEntity.builder()
                .notificationId(notification.getId() == null ? null : notification.getId().getValue())
                .sender(notification.getSender().getValue())
                .receiver(notification.getReceiver().getValue())
                .channel(Channel.valueOf(notification.getChannel().toString()))
                .deliveryReqDtm(notification.getDeliveryReqDtm().getDatetime())
                .client(notification.getClient().getValue())
                .title(notification.getMessage().getTitle().getValue())
                .contents(notification.getMessage().getContents().getValue())
                .createDt(LocalDateTime.now())
                .build();
    }

    Notification mapToDomain(NotificationEntity notificationEntity) {
        return Notification.createNotificationByEntity(notificationEntity);
    }

    public NotificationView mapToQuertView(NotificationEntity notificationEntity) {
        return new NotificationView(
                notificationEntity.getNotificationId(),
                notificationEntity.getSender(),
                notificationEntity.getReceiver(),
                notificationEntity.getChannel().toString(),
                notificationEntity.getTitle(),
                notificationEntity.getContents(),
                notificationEntity.getDeliveryReqDtm().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                notificationEntity.getClient(),
                notificationEntity.getCreateDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }
}

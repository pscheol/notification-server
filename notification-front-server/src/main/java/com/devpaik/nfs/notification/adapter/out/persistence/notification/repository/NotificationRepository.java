package com.devpaik.nfs.notification.adapter.out.persistence.notification.repository;

import com.devpaik.nfs.notification.adapter.out.persistence.notification.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("""
           delete from NotificationEntity n
           where n.deliveryReqDtm <= :endDtm
        """)
    void deleteBy3MonthDeliveryReqDtm(LocalDateTime endDtm);
}

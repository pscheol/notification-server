package com.devpaik.nfs.notification.adapter.out.persistence.event;

import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.EventType;
import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.NotificationEventEntity;
import com.devpaik.nfs.notification.adapter.out.persistence.event.entity.State;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NotificationEventRepository extends JpaRepository<NotificationEventEntity, Long> {

    @Query("""
            select e
              from NotificationEventEntity e
             where e.state = :state
               and e.deliveryReqDtm <= :nowDtm
    """)
    Optional<List<NotificationEventEntity>> findByStateAndContainingDeliveryReqDtmPageable(State state, LocalDateTime nowDtm, Pageable request);


    @Query("""
            select e
              from NotificationEventEntity e
             where e.state = :state
               and e.eventType = :eventType
               and e.deliveryReqDtm <= :nowDtm
    """)
    Optional<List<NotificationEventEntity>> findByStateAndEventTypeAndContainingDeliveryReqDtmPageable(State state,
                                                                                                       EventType eventType,
                                                                                                       LocalDateTime nowDtm,
                                                                                                       Pageable request);



    @Lock(LockModeType.OPTIMISTIC)
    @Query("""
            select e
            from NotificationEventEntity e
            where e.eventId = :eventId
    """)
    Optional<NotificationEventEntity> findByIdWithOptimisticLock(Long eventId);
}

package com.devpaik.nfs.notification.adapter.out.persistence.event.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.Objects;

@Comment("알림 발송 이벤트")
@Entity
@Table(name = "tb_notification_event", indexes = {
        @Index(name = "idx_state_event_type_dvry_req_dtm", columnList = "state, event_type, delivery_req_dtm"),
        @Index(name = "idx_state_dvry_req_dtm", columnList = "state, delivery_req_dtm")
})
@DynamicUpdate
@DynamicInsert
public class NotificationEventEntity {

    @Comment("이벤트 ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    @Getter
    private Long eventId;


    @Comment("version")
    @Column(name = "version", nullable = false)
    @Version
    @Getter
    private Long version;

    @Comment("상태 : (요청: REQ, SUCCESS: 성공, FAIL: 전송실패)")
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, length = 10)
    @Getter
    private State state;

    @Comment("이벤트 처리 타입 : (즉시: NOW, SCHEDULE : 예약발송)")
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false, length = 10)
    @Getter
    private EventType eventType;

    @Comment("발송 요청 날짜")
    @Column(name = "delivery_req_dtm", nullable = false)
    @Getter
    private LocalDateTime deliveryReqDtm;


    @Lob
    @Comment("이벤트 데이터")
    @Column(name = "event_data", nullable = false)
    @Getter
    private String eventData;

    @Comment("이벤트 생성 날짜")
    @Column(name = "create_dt", nullable = false)
    @Getter
    public LocalDateTime createDt;

    @Builder
    public NotificationEventEntity(Long eventId, Long version, State state, EventType eventType, LocalDateTime deliveryReqDtm, String eventData, LocalDateTime createDt) {
        this.eventId = eventId;
        this.version = version;
        this.state = state;
        this.eventType = eventType;
        this.deliveryReqDtm = deliveryReqDtm;
        this.eventData = eventData;
        this.createDt = createDt;
    }




    protected NotificationEventEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationEventEntity entity = (NotificationEventEntity) o;
        return Objects.equals(eventId, entity.eventId) && Objects.equals(version, entity.version) && state == entity.state && eventType == entity.eventType && Objects.equals(deliveryReqDtm, entity.deliveryReqDtm) && Objects.equals(eventData, entity.eventData) && Objects.equals(createDt, entity.createDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, version, state, eventType, deliveryReqDtm, eventData, createDt);
    }

    @Override
    public String toString() {
        return "NotificationEventEntity{" +
                "eventId=" + eventId +
                ", version=" + version +
                ", state=" + state +
                ", eventType=" + eventType +
                ", deliveryReqDtm=" + deliveryReqDtm +
                ", eventData='" + eventData + '\'' +
                ", createDt=" + createDt +
                '}';
    }
}

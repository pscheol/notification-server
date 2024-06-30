package com.devpaik.nfs.notification.adapter.out.persistence.notification.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_notification", indexes = {
        @Index(name = "idx_delivery_req_dtm", columnList = "delivery_req_dtm")
})
@DynamicUpdate
@DynamicInsert
public class NotificationEntity {

    @Id
    @Comment("알림 ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", nullable = false)
    @Getter
    private Long notificationId;

    @Comment("수신자( 이메일주소, SMS, 카카오톡 ID)")
    @Column(name = "receiver", nullable = false, length = 100)
    @Getter
    private String receiver;

    @Comment("발신자")
    @Column(name = "sender", nullable = false, length = 100)
    @Getter
    private String sender;

    @Comment("채널 : (SMS, PUSH, EMAIL)")
    @Column(name = "channel", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    @Getter
    private Channel channel;

    @Comment("예약발송날짜(yyyy-MM-dd hh:mm)")
    @Column(name = "delivery_req_dtm",  nullable = false)
    @Getter
    private LocalDateTime deliveryReqDtm;

    @Comment("클라이언트(Mobile, Web, etc..)")
    @Column(name = "client", length = 10, nullable = false)
    @Getter
    private String client;

    @Comment("제목")
    @Column(name = "title", nullable = false)
    @Getter
    private String title;

    @Comment("내용")
    @Column(name = "contents", nullable = false)
    @Lob
    @Getter
    private String contents;

    @Comment("생성일")
    @Column(name = "create_dt", nullable = false)
    @Getter
    private LocalDateTime createDt;

    @Builder
    public NotificationEntity(Long notificationId, String receiver, String sender, Channel channel,
                              LocalDateTime deliveryReqDtm, String client, String title,
                              String contents, LocalDateTime createDt) {
        this.notificationId = notificationId;
        this.receiver = receiver;
        this.sender = sender;
        this.channel = channel;
        this.deliveryReqDtm = deliveryReqDtm;
        this.client = client;
        this.title = title;
        this.contents = contents;
        this.createDt = createDt;
    }


    protected NotificationEntity() {

    }


}

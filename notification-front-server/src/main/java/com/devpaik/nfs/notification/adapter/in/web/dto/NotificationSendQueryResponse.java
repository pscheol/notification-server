package com.devpaik.nfs.notification.adapter.in.web.dto;

import com.devpaik.nfs.notification.domain.query.NotificationView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

@Schema(description = "알림 완료 목록 Response")
@Getter
public class NotificationSendQueryResponse extends PageMaker {
    List<NotificationView> notifications;

    @Builder
    private NotificationSendQueryResponse(int page, int size, long totalCount, List<NotificationView> notifications) {
        super(page, size, totalCount);
        this.notifications = ObjectUtils.isEmpty(notifications) ? Collections.emptyList() : notifications;
    }


    public static NotificationSendQueryResponse toQuery(int page ,int size, List<NotificationView> notifications, long totalCount) {
        return NotificationSendQueryResponse.builder()
                .notifications(notifications)
                .page(page)
                .size(size)
                .totalCount(totalCount)
                .build();
    }
}

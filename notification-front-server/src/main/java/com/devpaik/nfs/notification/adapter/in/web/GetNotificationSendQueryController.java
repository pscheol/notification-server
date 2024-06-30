package com.devpaik.nfs.notification.adapter.in.web;

import com.devpaik.nfs.notification.adapter.in.web.dto.NotificationSendQueryResponse;
import com.devpaik.nfs.notification.adapter.in.web.dto.QueryParam;
import com.devpaik.nfs.notification.application.port.out.GetNotificationQueryPort;
import com.devpaik.nfs.notification.domain.query.NotificationView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "알림 전송 완료 Query Controller")
@Slf4j
@RestController
@RequestMapping("/api/v1/notifications/send")
public class GetNotificationSendQueryController {

    private final GetNotificationQueryPort getNotificationQueryPort;

    public GetNotificationSendQueryController(GetNotificationQueryPort getNotificationQueryPort) {
        this.getNotificationQueryPort = getNotificationQueryPort;
    }

    @Operation(summary = "알림 전송 완료 목록")
    @GetMapping
    public NotificationSendQueryResponse getNotificationQueryList(@Valid @ModelAttribute QueryParam queryParam) {
        log.debug("@@ getNotificationQueryList queryParam={}", queryParam);

        List<NotificationView> notificationViews = getNotificationQueryPort.selectNotificationSuccessList(queryParam.page(), queryParam.limit());
        long totalCount = getNotificationQueryPort.totalNotificationSuccess();

        return NotificationSendQueryResponse.toQuery(queryParam.page(), queryParam.limit(), notificationViews, totalCount);
    }
}

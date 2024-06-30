package com.devpaik.nfs.notification.domain.query;

public record NotificationView(
        Long notificationId, String sender, String receiver, String channel,
        String title, String contents, String deliveryReqDtm, String client,
        String createDt
) {


}

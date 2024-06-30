package com.devpaik.nfs.notification.application.port.out;

import java.time.LocalDateTime;

public interface UpdateNotificationStatePort {

    public void deleteNotification3Month(LocalDateTime endDtm);
}

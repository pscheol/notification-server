package com.devpaik.nss.producer;

public interface SendFrontNotificationByFail {
    public void sendFailMessage(Long eventId, String resultCode);
}

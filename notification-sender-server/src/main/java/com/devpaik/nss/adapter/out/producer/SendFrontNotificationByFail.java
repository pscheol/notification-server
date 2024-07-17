package com.devpaik.nss.adapter.out.producer;

public interface SendFrontNotificationByFail {
    public void sendFailMessage(Long eventId, String resultCode);
}

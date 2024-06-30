package com.devpaik.nfs.notification.adapter.in.web.dto;

import com.devpaik.nfs.notification.exceptions.ServiceCode;

public record RegisterForNotificationSendResponse(String code, String message) {


    public static RegisterForNotificationSendResponse build(ServiceCode serviceCode) {
        return new RegisterForNotificationSendResponse(serviceCode.getCode(), serviceCode.getMsg());
    }

}

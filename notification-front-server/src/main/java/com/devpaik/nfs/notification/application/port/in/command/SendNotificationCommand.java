package com.devpaik.nfs.notification.application.port.in.command;


import com.devpaik.nfs.notification.domain.*;

public record SendNotificationCommand(EventId eventId, Receiver receiver, Title title,
                                      Contents contents, Channel channel) {

    public static SendNotificationCommand of(EventId eventId, Receiver receiver, Title title,
                                             Contents contents, Channel channel) {
        return new SendNotificationCommand(eventId, receiver, title, contents, channel);
    }
}

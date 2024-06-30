package com.devpaik.nss.dto;

import com.devpaik.nss.common.Serializer;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    private final Serializer serializer;

    public MessageMapper(Serializer serializer) {
        this.serializer = serializer;
    }

    public String resultMessageToJson(ResultMessage src) {
        return serializer.serialize(src);
    }

    public SMSMessage jsonToSMSMessage(String src) {
        return serializer.deserialize(src, SMSMessage.class);
    }

    public EmailMessage jsonToEmailMessage(String src) {
        return serializer.deserialize(src, EmailMessage.class);
    }

    public PushMessage jsonToPushMessage(String src) {
        return serializer.deserialize(src, PushMessage.class);
    }
}

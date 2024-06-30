package com.devpaik.nfs.notification.adapter.out.infra.rabbitmq.dto;

import com.devpaik.nfs.common.Serializer;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    private final Serializer serializer;

    public MessageMapper(Serializer serializer) {
        this.serializer = serializer;
    }

    public String parseJson(SMSMessage src) {
        return serializer.serialize(src);
    }

    public String parseJson(EmailMessage src) {
        return serializer.serialize(src);
    }

    public String parseJson(PushMessage src) {
        return serializer.serialize(src);
    }

    public ResultMessage jsonToResultMessage(String src) {
        return serializer.deserialize(src, ResultMessage.class);
    }
}

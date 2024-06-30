package com.devpaik.nfs.notification.domain;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;


public class Message implements Serializable {
    @Getter
    private final Title title;

    @Getter
    private final Contents contents;

    private Message() {
        this(null, null);
    }

    public Message(Title title, Contents contents) {
        this.title = title;
        this.contents = contents;
    }


    public static Message of(String title, String contents) {
        return new Message(Title.of(title), Contents.of(contents));
    }


    @Override
    public String toString() {
        return "Message{" +
                "title=" + title +
                ", contents=" + contents +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(title, message.title) && Objects.equals(contents, message.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, contents);
    }
}

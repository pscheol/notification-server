package com.devpaik.nfs.notification.domain;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;


public class NotificationId implements Serializable {

    @Getter
    private final Long value;

    private NotificationId() {
        this(null);
    }

    public NotificationId(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NotificationId{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationId that = (NotificationId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

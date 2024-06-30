package com.devpaik.nfs.notification.domain;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

public class EventId implements Serializable {
    @Getter
    private final Long id;

    private EventId() {
        this(null);
    }

    public EventId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EventId{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventId eventId = (EventId) o;
        return Objects.equals(id, eventId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.devpaik.nfs.notification.domain;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class CreateDt implements Serializable {
    @Getter
    private final LocalDateTime dateTime;

    private CreateDt() {
        this(null);
    }

    public CreateDt(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "CreateDt{" +
                "dateTime=" + dateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateDt createDt = (CreateDt) o;
        return Objects.equals(dateTime, createDt.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime);
    }
}

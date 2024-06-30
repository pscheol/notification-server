package com.devpaik.nfs.notification.domain;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;


public class Version implements Serializable {

    @Getter
    private final Long value;

    private Version() {
        this(null);
    }

    public Version(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return value == version.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Version{" +
                "value=" + value +
                '}';
    }
}

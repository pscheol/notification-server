package com.devpaik.nfs.notification.domain;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;


public class Sender implements Serializable {
    @Getter
    private final String value;

    private Sender() {
        this(null);
    }
    public Sender(String value) {
        this.value = value;
    }

    public static Sender of(String value) {
        return new Sender(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sender sender = (Sender) o;
        return Objects.equals(value, sender.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Sender{" +
                "value='" + value + '\'' +
                '}';
    }
}

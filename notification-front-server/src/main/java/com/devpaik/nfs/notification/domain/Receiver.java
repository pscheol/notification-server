package com.devpaik.nfs.notification.domain;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;


public class Receiver implements Serializable {
    @Getter
    private final String value;

    private Receiver() {
        this(null);
    }

    public Receiver(String value) {
        this.value = value;
    }

    public static Receiver of(String receiver) {
        return new Receiver(receiver);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receiver receiver = (Receiver) o;
        return Objects.equals(value, receiver.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "value='" + value + '\'' +
                '}';
    }
}

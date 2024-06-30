package com.devpaik.nfs.notification.domain;

import lombok.Getter;

import java.util.Objects;

public class Contents {
    @Getter
    private final String value;

    private Contents() {
        this(null);
    }

    public Contents(String value) {
        this.value = value;
    }


    public static Contents of(String contents) {
        return new Contents(contents);
    }


    @Override
    public String toString() {
        return "Contents{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contents contents = (Contents) o;
        return Objects.equals(value, contents.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

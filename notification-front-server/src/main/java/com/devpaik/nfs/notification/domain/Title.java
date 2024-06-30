package com.devpaik.nfs.notification.domain;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;


public class Title implements Serializable {
    @Getter
    private final String value;

    private Title() {
        this(null);
    }

    public Title(String value) {
        this.value = value;
    }

    public static Title of(String title) {
        return new Title(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title = (Title) o;
        return Objects.equals(value, title.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Title{" +
                "value='" + value + '\'' +
                '}';
    }
}

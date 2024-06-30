package com.devpaik.nfs.notification.domain;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;


public class Client implements Serializable {
    @Getter
    private final String value;

    private Client() {
        this(null);
    }


    public Client(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(value, client.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Client{" +
                "value='" + value + '\'' +
                '}';
    }

    public static Client of(String client) {
        return new Client(client);
    }
}

package com.devpaik.nfs.notification.domain;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class DeliveryReqDtm implements Serializable {

    @Getter
    private final LocalDateTime datetime;


    private DeliveryReqDtm() {
        this(null);
    }

    public DeliveryReqDtm(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public static DeliveryReqDtm of(LocalDateTime datetime) {
        return new DeliveryReqDtm(datetime);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryReqDtm that = (DeliveryReqDtm) o;
        return Objects.equals(datetime, that.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datetime);
    }

    @Override
    public String toString() {
        return "DeliveryReqDtm{" +
                "datetime=" + datetime +
                '}';
    }
}

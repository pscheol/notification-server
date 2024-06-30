package com.devpaik.nss.simulation;

public record SendResponse(String resultCode) {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
}

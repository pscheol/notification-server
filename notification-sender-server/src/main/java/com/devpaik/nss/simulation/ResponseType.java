package com.devpaik.nss.simulation;

import lombok.Getter;

@Getter
public class ResponseType {

    private final ResultType resultType;
    private final LatencyType latencyType;

    public ResponseType() {
        this.resultType = ResultType.getRandomResultType();
        this.latencyType = new LatencyType();
    }
}

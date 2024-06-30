package com.devpaik.nfs.notification.domain;

public enum State {

    REQ, SEND, SUCCESS, FAIL;

    public boolean checkSuccess() {
        return State.SUCCESS.equals(this);
    }

    public boolean checkNotSuccess() {
        return State.FAIL.equals(this) || State.REQ.equals(this) || State.SEND.equals(this);
    }
    public boolean checkNotFail() {
        return !State.FAIL.equals(this);
    }
}

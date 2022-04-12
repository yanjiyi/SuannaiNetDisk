package com.suannai.netdisk.dao;

public class Message {
    public int StatusCode;
    public String operation;
    public String errorMsg;

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "UserCtlMessage{" +
                "StatusCode=" + StatusCode +
                ", operation='" + operation + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}

package com.secure.userdata.record.requests;

import com.secure.userdata.record.IUserDataCallBack;

public interface IUserRequest {
    public enum RequestType {
        REQUEST_ADD,
        REQUEST_DELETE,
        REQUEST_GET_BY_NAME,
        REQUEST_GET_ALL,
        REQUEST_GET_COUNT
    }

    Long getRequestID();
    RequestType getRequestType();
    IUserDataCallBack getCallback();
}

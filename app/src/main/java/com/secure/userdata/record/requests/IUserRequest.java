package com.secure.userdata.record.requests;

import com.secure.userdata.record.IUserDataCallBack;

public interface IUserRequest {
    public enum RequestType {
        REQUEST_ADD,
        REQUEST_DELETE,
        REQUEST_GET,
        REQUEST_GET_ALL
    }

    public RequestType getRequestType();
    public IUserDataCallBack getCallback();
}

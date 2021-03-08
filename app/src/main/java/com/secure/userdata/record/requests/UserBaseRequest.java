package com.secure.userdata.record.requests;

import com.secure.userdata.record.IUserDataCallBack;

public abstract class UserBaseRequest implements IUserRequest {

    private RequestType requestType;
    private IUserDataCallBack callBack;

    public UserBaseRequest(RequestType requestType, IUserDataCallBack callBack) {
        this.requestType = requestType;
        this.callBack = callBack;
    }

    @Override
    public RequestType getRequestType() {
        return requestType;
    }

    @Override
    public IUserDataCallBack getCallback() {
        return callBack;
    }
}

package com.secure.userdata.record.requests;

import com.secure.userdata.record.IUserDataCallBack;

import java.util.Random;

public abstract class UserBaseRequest implements IUserRequest {

    private int requestID;
    private RequestType requestType;
    private IUserDataCallBack callBack;

    public UserBaseRequest(RequestType requestType, IUserDataCallBack callBack) {
        requestID = new Random().nextInt();
        this.requestType = requestType;
        this.callBack = callBack;
    }

    @Override
    public int getRequestID() {
        return requestID;
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

package com.secure.userdata.record.requests;

import com.secure.userdata.record.IUserDataCallBack;

public abstract class UserBaseRequest implements IUserRequest {

    private Long requestID;
    private RequestType requestType;
    private IUserDataCallBack callBack;

    private IUserResponse userResponse;

    public UserBaseRequest(RequestType requestType, IUserDataCallBack callBack) {
        requestID = System.nanoTime();
        this.requestType = requestType;
        this.callBack = callBack;
    }

    @Override
    public Long getRequestID() {
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

    @Override
    public void setResponse(IUserResponse userResponse) {
        this.userResponse = userResponse;
    }

    @Override
    public IUserResponse getResponse() {
        return userResponse;
    }

    @Override
    public void onComplete() {
        callBack.onResponse(this);
    }
}

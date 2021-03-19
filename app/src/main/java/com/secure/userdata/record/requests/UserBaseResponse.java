package com.secure.userdata.record.requests;

public abstract class UserBaseResponse implements  IUserResponse {

    private boolean result;

    public UserBaseResponse(boolean result) {
        this.result = result;
    }

    @Override
    public boolean isSuccess() {
        return result;
    }
}

package com.secure.userdata.record;

import com.secure.userdata.record.requests.IUserRequest;

public interface IUserDataCallBack {
    void onResponse(IUserRequest userRequest);
}

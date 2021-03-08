package com.secure.userdata.record.requests;

import com.secure.userdata.record.IUserDataCallBack;

public class UserGetAllRecordsRequest extends UserBaseRequest {
    public UserGetAllRecordsRequest(RequestType requestType, IUserDataCallBack callBack) {
        super(requestType, callBack);
    }
}

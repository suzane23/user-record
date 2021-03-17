package com.secure.userdata.record.requests;

import com.secure.userdata.record.IUserDataCallBack;

public class UserGetRecordsCountRequest extends UserBaseRequest{
    public UserGetRecordsCountRequest(RequestType requestType, IUserDataCallBack callBack) {
        super(requestType, callBack);
    }
}

package com.secure.userdata.record.requests;

import com.secure.userdata.record.IUserDataCallBack;

public class UserDeleteRecordByNameRequest extends UserBaseRequest {
    private String deletedName;
    public UserDeleteRecordByNameRequest(RequestType requestType, IUserDataCallBack callBack, String deletedName) {
        super(requestType, callBack);
        this.deletedName = deletedName;
    }

    public String getDeletedName() {
        return deletedName;
    }
}

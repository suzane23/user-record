package com.secure.userdata.record.requests;

import com.secure.userdata.record.IUserDataCallBack;
import com.secure.userdata.record.UserRecord;

public class UserAddRecordRequest extends UserBaseRequest {

    private UserRecord userRecord;

    public UserAddRecordRequest(RequestType requestType, UserRecord userRecord, IUserDataCallBack callBack) {
        super(requestType, callBack);
        this.userRecord = userRecord;
    }

    public UserRecord getUserRecord() {
        return userRecord;
    }

    public void setUserRecord(UserRecord userRecord) {
        this.userRecord = userRecord;
    }
}

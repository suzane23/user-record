package com.secure.userdata.record.requests;

import com.secure.userdata.record.IUserDataCallBack;

public class UserGetRecordsByName extends UserBaseRequest {

    private String searchName;

    public UserGetRecordsByName(RequestType requestType, IUserDataCallBack callBack, String searchName) {
        super(requestType, callBack);
        this.searchName = searchName;
    }

    @Override
    public IUserDataCallBack getCallback() {
        return null;
    }

    public String getSearchName() {
        return searchName;
    }
}

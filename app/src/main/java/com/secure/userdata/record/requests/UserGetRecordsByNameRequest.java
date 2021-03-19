package com.secure.userdata.record.requests;

import com.secure.userdata.record.IUserDataCallBack;

public class UserGetRecordsByNameRequest extends UserBaseRequest {

    private String searchName;

    public UserGetRecordsByNameRequest(RequestType requestType, IUserDataCallBack callBack, String searchName) {
        super(requestType, callBack);
        this.searchName = searchName;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
}

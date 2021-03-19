package com.secure.userdata.record.requests;

import com.secure.userdata.record.UserRecord;

import java.util.List;

public class UserGetRecordsByNameResponse extends UserBaseResponse{
    private List<UserRecord> list;

    public UserGetRecordsByNameResponse(boolean result, List<UserRecord> list) {
        super(result);
        this.list = list;
    }

    public List<UserRecord> getRecordList() {
        return list;
    }
}

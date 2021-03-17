package com.secure.userdata.record;

import com.secure.userdata.record.requests.IUserRequest;

import java.util.List;

public interface IUserDataCallBack {

    void onAddRecordResult(IUserRequest userRequest, Boolean result, String recordID);
    void onDeleteRecordResult(int result);
    void onGetRecordsByNameResult(List<UserRecord> list);
    void onGetAllRecordsResult(List<UserRecord> list);
    void onGetRecordsCount(int count);
}

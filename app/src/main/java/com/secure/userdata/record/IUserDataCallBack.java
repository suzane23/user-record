package com.secure.userdata.record;

import java.util.List;

public interface IUserDataCallBack {

    void onAddRecordResult(Boolean result, String recordID);
    void onDeleteRecordResult(int result);
    void onGetRecordsByNameResult(List<UserRecord> list);
    void onGetAllRecordsResult(List<UserRecord> list, int requestID);
    void onGetRecordsCount(int count);
}

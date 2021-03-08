package com.secure.userdata.record;

import java.util.List;

public interface IUserDataCallBack {

    void onAddRecordResult(Boolean result, String recordID);
    void onDeleteRecordResult();
    void onGetRecordsByNameResult(List<UserRecord> list);
    void getAllRecordsResult(List<UserRecord> list);
}

package com.secure.userdata.record;

import android.content.Context;

import java.util.List;

public class UserDataController implements IUserDataCallBack{
    private IUserData userData;
    public UserDataController(Context context) {
        userData = RecordFactory.getInstance(RecordFactory.RecordStorageType.FILE);
        userData.init(context);
    }

    public UserDataController(Context context, RecordFactory.RecordStorageType recordStorageType){
        userData = RecordFactory.getInstance(recordStorageType);
        userData.init(context);
    }

    public void add(UserRecord record ) {
        userData.addRecord(record, this);
    }

    public void getallRecords() {
        userData.getAllRecords(this);
    }

    @Override
    public void onAddRecordResult(Boolean result, String recordID) {
        System.out.println("onAddRecordResult result = " + result + ", recordID = " + recordID);
    }

    @Override
    public void getAllRecordsResult(List<UserRecord> list) {
        System.out.println("getAllRecordsResult " + list );
    }

    @Override
    public void onDeleteRecordResult() {

    }

    @Override
    public void onGetRecordsByNameResult(List<UserRecord> list) {

    }
}

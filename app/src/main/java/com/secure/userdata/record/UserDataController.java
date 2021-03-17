package com.secure.userdata.record;

import android.content.Context;

import java.util.List;

public class UserDataController implements IUserDataCallBack{
    private IUserData userData;

    public UserDataController(Context context, RecordFactory.RecordStorageType recordStorageType){
        userData = RecordFactory.getInstance(recordStorageType);
        userData.init(context);
    }

    public void add(UserRecord record ) {
        userData.addRecord(record, this);
    }

    public void getAllRecords() {
        int requestID = userData.getAllRecords(this);
        System.out.println("getAllRecords requestID = " + requestID);
    }
    public void deleteRecord(String name) {
        userData.deleteRecordByName(name, this);
    }

    public void getRecordsByName(String name){
        userData.getRecordByName(name, this);
    }

    public void getRecordsCount() {
        userData.getCount(this);
    }

    @Override
    public void onAddRecordResult(Boolean result, String recordID) {
        System.out.println("onAddRecordResult result = " + result + ", recordID = " + recordID);
    }

    @Override
    public void onGetAllRecordsResult(List<UserRecord> list, int requestID) {
        System.out.println("onGetAllRecordsResult " + list );

        System.out.println("onGetAllRecordsResult requestID = " + requestID);
    }

    @Override
    public void onDeleteRecordResult(int result) {
        System.out.println("onDeleteRecordResult " + result );

    }

    @Override
    public void onGetRecordsCount(int count) {
        System.out.println("onGetRecordsCount "  + count );
    }

    @Override
    public void onGetRecordsByNameResult(List<UserRecord> list) {
        System.out.println("onGetRecordsByNameResult " );
        for (UserRecord record: list ) {
            System.out.println(record);
        }
    }
}

package com.secure.userdata.record;

import android.content.Context;

import com.secure.userdata.record.requests.IUserRequest;

import java.util.LinkedList;
import java.util.List;

public class UserDataController implements IUserDataCallBack{
    private IUserData userData;

    private List<IUserRequest> requestList = new LinkedList<>();

    public UserDataController(Context context, RecordFactory.RecordStorageType recordStorageType){
        userData = RecordFactory.getInstance(recordStorageType);
        userData.init(context);
    }

    public void add(UserRecord record ) {
        IUserRequest userRequest = userData.addRecord(record, this);

        requestList.add(userRequest);
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
    public void onAddRecordResult(IUserRequest userRequest, Boolean result, String recordID) {
        System.out.println("onAddRecordResult result = " + result + ", recordID = " + recordID);

        boolean contains = requestList.contains(userRequest);
        System.out.println("contains = " + contains);
    }

    @Override
    public void onGetAllRecordsResult(List<UserRecord> list) {
        System.out.println("onGetAllRecordsResult " + list );

        System.out.println("onGetAllRecordsResult");
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

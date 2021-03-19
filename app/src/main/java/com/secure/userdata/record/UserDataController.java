package com.secure.userdata.record;

import android.content.Context;

import com.secure.userdata.record.requests.IUserRequest;
import com.secure.userdata.record.requests.UserAddRecordRequest;
import com.secure.userdata.record.requests.UserDeleteRecordByNameRequest;
import com.secure.userdata.record.requests.UserDeleteRecordByNameResponse;
import com.secure.userdata.record.requests.UserGetAllRecordsResponse;
import com.secure.userdata.record.requests.UserGetRecordsByNameResponse;
import com.secure.userdata.record.requests.UserGetRecordsCountResponse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDataController implements IUserDataCallBack{
    private IUserData userData;

    private List<IUserRequest> list = new LinkedList<>();

    public UserDataController(Context context, RecordFactory.RecordStorageType recordStorageType){
        userData = RecordFactory.getInstance(recordStorageType);
        userData.init(context);
    }

    public void cancelRequest(){
        userData.cancelRequest(null);
    }

    public void add(UserRecord record ) {
        IUserRequest userRequest = userData.addRecord(record, this);
        list.add(userRequest);
    }

    public void getAllRecords() {
        IUserRequest userRequest = userData.getAllRecords(this);
        list.add(userRequest);
    }
    public void deleteRecord(String name) {
        IUserRequest userRequest = userData.deleteRecordByName(name, this);
        list.add(userRequest);
    }

    public void getRecordsByName(String name){
        IUserRequest userRequest = userData.getRecordByName(name, this);
        list.add(userRequest);
    }

    public void getRecordsCount() {
        IUserRequest userRequest = userData.getCount(this);
        list.add(userRequest);
    }

    @Override
    public void onResponse(IUserRequest userRequest) {
        System.out.println("response for = " + userRequest.getRequestType());
        switch (userRequest.getRequestType()){
            case REQUEST_ADD:{

                System.out.println("Record added : " + userRequest.getResponse().isSuccess() + " with id = " + ((UserAddRecordRequest)userRequest).getUserRecord().id);
            }
            break;
            case REQUEST_DELETE:{
                UserDeleteRecordByNameResponse response = (UserDeleteRecordByNameResponse) userRequest.getResponse();
                System.out.println(response.getCount() + " Records deleted with name " + ((UserDeleteRecordByNameRequest)userRequest).getDeletedName() );
            }
            break;
            case REQUEST_GET_ALL:{
                UserGetAllRecordsResponse response = (UserGetAllRecordsResponse) userRequest.getResponse();
                List<UserRecord> list = response.getRecordList();
                for (UserRecord rec: list) {
                    System.out.println(rec);
                }
            }
            break;
            case REQUEST_GET_BY_NAME:{
                UserGetRecordsByNameResponse response = (UserGetRecordsByNameResponse) userRequest.getResponse();
                List<UserRecord> list = new ArrayList<>();
                for (UserRecord rec: list) {
                    System.out.println(rec);
                }
            }
            break;
            case REQUEST_GET_COUNT:{
                UserGetRecordsCountResponse response = (UserGetRecordsCountResponse) userRequest.getResponse();
                System.out.println(response.getCount() + " records present" );
            }
        }
    }
}

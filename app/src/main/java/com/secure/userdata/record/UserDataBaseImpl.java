package com.secure.userdata.record;

import android.content.Context;

import com.secure.userdata.record.requests.IUserRequest;
import com.secure.userdata.record.requests.UserAddRecordRequest;
import com.secure.userdata.record.requests.UserDeleteRecordByNameRequest;
import com.secure.userdata.record.requests.UserGetAllRecordsRequest;
import com.secure.userdata.record.requests.UserGetRecordsByName;
import com.secure.userdata.record.requests.UserGetRecordsCountRequest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class UserDataBaseImpl implements IUserData, Runnable{

    public class AddRecordItem {
        UserRecord record;
        IUserDataCallBack callBack;
    }


    private LinkedBlockingQueue<IUserRequest> queue = null;
    private Thread thread = null;
    protected Context context = null;

    @Override
    public void init(Context context) {
        this.context = context;
        queue = new LinkedBlockingQueue<>();
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(true){
            IUserRequest userRequest;
            try {
                userRequest = queue.take();

                if(userRequest.getRequestType() == IUserRequest.RequestType.REQUEST_ADD) {
                    UserAddRecordRequest addRecordRequest = (UserAddRecordRequest)userRequest;
                    addRecordTo(addRecordRequest.getUserRecord());

                    addRecordRequest.getCallback().onAddRecordResult(true, addRecordRequest.getUserRecord().id);

                }
                else if(userRequest.getRequestType() == IUserRequest.RequestType.REQUEST_DELETE) {
                    UserDeleteRecordByNameRequest deleteRecordByNameRequest = (UserDeleteRecordByNameRequest)userRequest;
                    int result = deleteRecordByName(deleteRecordByNameRequest.getDeletedName());

                    deleteRecordByNameRequest.getCallback().onDeleteRecordResult(result);
                }
                else if(userRequest.getRequestType() == IUserRequest.RequestType.REQUEST_GET_BY_NAME) {
                    UserGetRecordsByName getRecordsByName = (UserGetRecordsByName)userRequest;
                    List<UserRecord> list = getRecordsByName(getRecordsByName.getSearchName());

                    getRecordsByName.getCallback().onGetRecordsByNameResult(list);
                }
                else if(userRequest.getRequestType() == IUserRequest.RequestType.REQUEST_GET_ALL) {

                    List<UserRecord> list = getAllRecords();
                    userRequest.getCallback().onGetAllRecordsResult(list, userRequest.getRequestID());
                }
                else if (userRequest.getRequestType() == IUserRequest.RequestType.REQUEST_GET_COUNT) {

                    int count = getRecordsCount();
                    userRequest.getCallback().onGetRecordsCount(count);
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    abstract void addRecordTo(UserRecord record);
    abstract int deleteRecordByName(String name);
    abstract List<UserRecord> getRecordsByName(String name);
    abstract List<UserRecord> getAllRecords();
    abstract int getRecordsCount();

    @Override
    public int addRecord(UserRecord record, IUserDataCallBack callBack) {
        System.out.println("addRecord ++");

        UserAddRecordRequest addRecordRequest = new UserAddRecordRequest(IUserRequest.RequestType.REQUEST_ADD, record, callBack);
        queue.add(addRecordRequest);

        System.out.println("addRecord --");

        return new Random().nextInt();
    }

    @Override
    public void getRecordByName(String name, IUserDataCallBack callBack) {
        System.out.println("getRecordByName ++");

        UserGetRecordsByName getRecordsByName = new UserGetRecordsByName(IUserRequest.RequestType.REQUEST_GET_BY_NAME, callBack, name);
        queue.add(getRecordsByName);

        System.out.println("getRecordByName --");

    }

//    @Override
    public void getRecordById(String id, IUserDataCallBack callBack) {

    }

    @Override
    public int getAllRecords(IUserDataCallBack callBack) {
        System.out.println("getAllRecords ++");

        UserGetAllRecordsRequest getAllRecordsRequest = new UserGetAllRecordsRequest(IUserRequest.RequestType.REQUEST_GET_ALL, callBack);
        queue.add(getAllRecordsRequest);

        System.out.println("getAllRecords --");
        return getAllRecordsRequest.getRequestID();
    }

    @Override
    public void getCount(IUserDataCallBack callBack) {
        System.out.println("getCount ++");

        UserGetRecordsCountRequest countRequest = new UserGetRecordsCountRequest(IUserRequest.RequestType.REQUEST_GET_COUNT, callBack);
        queue.add(countRequest);

        System.out.println("getCount --");

    }

    @Override
    public void deleteRecordByName(String name, IUserDataCallBack callBack) {
        System.out.println("deleteRecordByName ++");

        UserDeleteRecordByNameRequest deleteRecordByNameRequest = new UserDeleteRecordByNameRequest(IUserRequest.RequestType.REQUEST_DELETE, callBack, name);
        queue.add(deleteRecordByNameRequest);

        System.out.println("deleteRecordByName --");


    }
}

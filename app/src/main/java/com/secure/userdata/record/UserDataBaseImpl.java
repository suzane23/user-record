package com.secure.userdata.record;

import android.content.Context;

import com.secure.userdata.record.requests.IUserRequest;
import com.secure.userdata.record.requests.IUserResponse;
import com.secure.userdata.record.requests.UserAddRecordRequest;
import com.secure.userdata.record.requests.UserAddRecordResponse;
import com.secure.userdata.record.requests.UserDeleteRecordByNameRequest;
import com.secure.userdata.record.requests.UserDeleteRecordByNameResponse;
import com.secure.userdata.record.requests.UserGetAllRecordsRequest;
import com.secure.userdata.record.requests.UserGetAllRecordsResponse;
import com.secure.userdata.record.requests.UserGetRecordsByNameRequest;
import com.secure.userdata.record.requests.UserGetRecordsByNameResponse;
import com.secure.userdata.record.requests.UserGetRecordsCountRequest;
import com.secure.userdata.record.requests.UserGetRecordsCountResponse;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class UserDataBaseImpl implements IUserData, Runnable{

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

                IUserResponse userResponse = null;

                switch (userRequest.getRequestType()) {
                    case REQUEST_ADD: {
                        UserAddRecordRequest addRecordRequest = (UserAddRecordRequest)userRequest;
                        addRecordTo(addRecordRequest.getUserRecord());

                        userResponse = new UserAddRecordResponse(true);
                        addRecordRequest.setResponse(userResponse);
                    }
                    break;

                    case REQUEST_DELETE: {
                        UserDeleteRecordByNameRequest deleteRecordByNameRequest = (UserDeleteRecordByNameRequest)userRequest;
                        int result = deleteRecordByName(deleteRecordByNameRequest.getDeletedName());

                        userResponse = new UserDeleteRecordByNameResponse(true, result);
                        deleteRecordByNameRequest.setResponse(userResponse);
                    }
                    break;

                    case REQUEST_GET_BY_NAME: {
                        UserGetRecordsByNameRequest getRecordsByNameRequest = (UserGetRecordsByNameRequest)userRequest;
                        List<UserRecord> list = getRecordsByName(getRecordsByNameRequest.getSearchName());

                        userResponse = new UserGetRecordsByNameResponse(true, list);
                        getRecordsByNameRequest.setResponse(userResponse);
                    }
                    break;

                    case REQUEST_GET_ALL: {
                        List<UserRecord> list = getAllRecords();
                        userResponse = new UserGetAllRecordsResponse(true, list);
                        userRequest.setResponse(userResponse);
                    }
                    break;

                    case REQUEST_GET_COUNT: {
                        int count = getRecordsCount();
                        userResponse = new UserGetRecordsCountResponse(true, count);
                        userRequest.setResponse(userResponse);
                    }
                    break;

                    default:
                }

                userRequest.onComplete();
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
    public IUserRequest addRecord(UserRecord record, IUserDataCallBack callBack) {
        System.out.println("addRecord ++");

        IUserRequest request = new UserAddRecordRequest(IUserRequest.RequestType.REQUEST_ADD, record, callBack);
        queue.add(request);

        System.out.println("addRecord --");

        return request;
    }

    @Override
    public IUserRequest getRecordByName(String name, IUserDataCallBack callBack) {
        System.out.println("getRecordByName ++");

        IUserRequest request = new UserGetRecordsByNameRequest(IUserRequest.RequestType.REQUEST_GET_BY_NAME, callBack, name);
        queue.add(request);

        System.out.println("getRecordByName --");

        return  request;

    }

//    @Override
    public void getRecordById(String id, IUserDataCallBack callBack) {

    }

    @Override
    public IUserRequest getAllRecords(IUserDataCallBack callBack) {
        System.out.println("getAllRecords ++");

        IUserRequest request = new UserGetAllRecordsRequest(IUserRequest.RequestType.REQUEST_GET_ALL, callBack);
        queue.add(request);

        System.out.println("getAllRecords --");
        return request;
    }

    @Override
    public IUserRequest getCount(IUserDataCallBack callBack) {
        System.out.println("getCount ++");

        IUserRequest request = new UserGetRecordsCountRequest(IUserRequest.RequestType.REQUEST_GET_COUNT, callBack);
        queue.add(request);

        System.out.println("getCount --");
        return  request;

    }

    @Override
    public IUserRequest deleteRecordByName(String name, IUserDataCallBack callBack) {
        System.out.println("deleteRecordByName ++");

        IUserRequest request = new UserDeleteRecordByNameRequest(IUserRequest.RequestType.REQUEST_DELETE, callBack, name);
        queue.add(request);

        System.out.println("deleteRecordByName --");
        return  request;


    }

    @Override
    public void cancelRequest(IUserRequest userRequest) {

        if(null != userRequest) {
            queue.remove(userRequest);
        }
        else {
            queue.clear();
        }
    }
}

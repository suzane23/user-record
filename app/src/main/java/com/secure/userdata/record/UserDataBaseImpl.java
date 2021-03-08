package com.secure.userdata.record;

import android.content.Context;
import android.service.autofill.UserData;

import com.secure.userdata.record.requests.IUserRequest;
import com.secure.userdata.record.requests.UserAddRecordRequest;
import com.secure.userdata.record.requests.UserDeleteRecordByNameRequest;
import com.secure.userdata.record.requests.UserGetAllRecordsRequest;
import com.secure.userdata.record.requests.UserGetRecordsByName;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class UserDataBaseImpl implements IUserData, Runnable{

    public class AddRecordItem {
        UserRecord record;
        IUserDataCallBack callBack;
    }


    private LinkedBlockingQueue<IUserRequest> queue = null;
    private Thread thread = null;

    @Override
    public void init(Context context) {
        queue = new LinkedBlockingQueue<>();
        thread = new Thread(thread);
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

                    userRequest.getCallback().onAddRecordResult(true, addRecordRequest.getUserRecord().id);

                }
                else if(userRequest.getRequestType() == IUserRequest.RequestType.REQUEST_DELETE) {
                    UserDeleteRecordByNameRequest deleteRecordByNameRequest = (UserDeleteRecordByNameRequest)userRequest;
                    deleteRecordByName(deleteRecordByNameRequest.getDeletedName());

                    userRequest.getCallback().onDeleteRecordResult();
                }
                else if(userRequest.getRequestType() == IUserRequest.RequestType.REQUEST_GET) {
                    UserGetRecordsByName getRecordsByName = (UserGetRecordsByName)userRequest;

                    List<UserRecord> list = getRecordsByName(getRecordsByName.getSearchName());
                    userRequest.getCallback().onGetRecordsByNameResult(list);
                }
                else if(userRequest.getRequestType() == IUserRequest.RequestType.REQUEST_GET_ALL) {

                    List<UserRecord> list = getAllRecords();
                    userRequest.getCallback().getAllRecordsResult(list);
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    abstract void addRecordTo(UserRecord record);
    abstract void deleteRecordByName(String name);
    abstract List<UserRecord> getRecordsByName(String name);
    abstract List<UserRecord> getAllRecords();

    @Override
    public void addRecord(UserRecord record, IUserDataCallBack callBack) {
        System.out.println("addRecord ++");

        UserAddRecordRequest addRecordRequest = new UserAddRecordRequest(IUserRequest.RequestType.REQUEST_ADD, record, callBack);
        queue.add(addRecordRequest);

        System.out.println("addRecord --");
    }

    @Override
    public void getRecordByName(String name, IUserDataCallBack callBack) {

    }

    @Override
    public void getRecordById(String id, IUserDataCallBack callBack) {

    }

    @Override
    public void getAllRecords(IUserDataCallBack callBack) {

    }

    @Override
    public void getCount(IUserDataCallBack callBack) {

    }

    @Override
    public void deleteRecordById(String id, IUserDataCallBack callBack) {

    }
}

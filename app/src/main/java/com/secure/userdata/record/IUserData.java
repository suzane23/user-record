package com.secure.userdata.record;

import android.content.Context;

import com.secure.userdata.record.requests.IUserRequest;

public interface IUserData {
      void init(Context context);
      IUserRequest addRecord(UserRecord record, IUserDataCallBack callBack);
      IUserRequest getRecordByName(String name, IUserDataCallBack callBack);
//      void getRecordById(String id, IUserDataCallBack callBack);
      IUserRequest getAllRecords(IUserDataCallBack callBack);
      IUserRequest getCount(IUserDataCallBack callBack);
      IUserRequest deleteRecordByName(String name, IUserDataCallBack callBack);

      void cancelRequest(IUserRequest userRequest);
}

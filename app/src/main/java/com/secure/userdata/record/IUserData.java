package com.secure.userdata.record;

import android.content.Context;

import com.secure.userdata.record.requests.IUserRequest;

public interface IUserData {
      void init(Context context);
      IUserRequest addRecord(UserRecord record, IUserDataCallBack callBack);
      void getRecordByName(String name, IUserDataCallBack callBack);
//      void getRecordById(String id, IUserDataCallBack callBack);
      int getAllRecords(IUserDataCallBack callBack);
      void getCount(IUserDataCallBack callBack);
      void deleteRecordByName(String name, IUserDataCallBack callBack);

      void cancelRequest(IUserRequest userRequest);
}

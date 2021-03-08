package com.secure.userdata.record;

import android.content.Context;

public interface IUserData {
      void init(Context context);
      void addRecord(UserRecord record, IUserDataCallBack callBack);
      void getRecordByName(String name, IUserDataCallBack callBack);
      void getRecordById(String id, IUserDataCallBack callBack);
      void getAllRecords(IUserDataCallBack callBack);
      void getCount(IUserDataCallBack callBack);
      void deleteRecordById(String id, IUserDataCallBack callBack);

}

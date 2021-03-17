package com.secure.userdata.record;

import android.content.Context;

public interface IUserData {
      void init(Context context);
      int addRecord(UserRecord record, IUserDataCallBack callBack);
      void getRecordByName(String name, IUserDataCallBack callBack);
//      void getRecordById(String id, IUserDataCallBack callBack);
      int getAllRecords(IUserDataCallBack callBack);
      void getCount(IUserDataCallBack callBack);
      void deleteRecordByName(String name, IUserDataCallBack callBack);

}

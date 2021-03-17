package com.secure.userdata.record;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserDataLocalDBImpl extends UserDataBaseImpl{

    SQLiteWrapper sqLiteWrapper = null;


    @Override
    public void init(Context context) {
        super.init(context);
        sqLiteWrapper = new SQLiteWrapper(context);
    }

    @Override
    void addRecordTo(UserRecord record) {
        SQLiteDatabase db = sqLiteWrapper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteWrapper.KEY_ID, record.id );
        values.put(SQLiteWrapper.KEY_NAME , record.name);
        values.put(SQLiteWrapper.KEY_TIME , record.time);
        db.insert(SQLiteWrapper.TABLE_RECORDS,null, values);
        db.close();

    }

    @Override
    int deleteRecordByName(String name) {
        SQLiteDatabase db = sqLiteWrapper.getWritableDatabase();
        int count = db.delete(SQLiteWrapper.TABLE_RECORDS, SQLiteWrapper.KEY_NAME + " = ?", new String[] {name});
        db.close();
        return count;
    }

    @Override
    List<UserRecord> getRecordsByName(String name) {
        List<UserRecord> list = new ArrayList<>();

        SQLiteDatabase db = sqLiteWrapper.getReadableDatabase();
        Cursor cursor =  db.query(SQLiteWrapper.TABLE_RECORDS, null,
                SQLiteWrapper.KEY_NAME + "=?", new String[]{name}, null , null, null, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0 ){

            do {
                UserRecord record = new UserRecord();
                int index = cursor.getColumnIndex(SQLiteWrapper.KEY_ID);

                if(index > -1) {
                    record.id = cursor.getString(index);
                }

                index = cursor.getColumnIndex(SQLiteWrapper.KEY_NAME);

                if(index > -1) {
                    record.name = cursor.getString(index);
                }

                index = cursor.getColumnIndex(SQLiteWrapper.KEY_TIME);

                if(index > -1) {
                    record.time = Long.valueOf(cursor.getString(index));
                }
                list.add(record);

            }while (cursor.moveToNext());

        }
        return list;
    }

    @Override
    List<UserRecord> getAllRecords() {
        List<UserRecord> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + SQLiteWrapper.TABLE_RECORDS;
        SQLiteDatabase db = sqLiteWrapper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        cursor.moveToFirst();
        if (cursor!= null && cursor.getCount() > 0 ) {
            do {
                UserRecord userRecord = new UserRecord();

                int index = cursor.getColumnIndex(SQLiteWrapper.KEY_ID);

                if(index > -1) {
                    userRecord.id = cursor.getString(index);
                }

                index = cursor.getColumnIndex(SQLiteWrapper.KEY_NAME);

                if(index > -1) {
                    userRecord.name = cursor.getString(index);
                }

                index = cursor.getColumnIndex(SQLiteWrapper.KEY_TIME);

                if(index > -1) {
                    userRecord.time = Long.valueOf(cursor.getString(index));
                }


                // Adding contact to list
                contactList.add(userRecord);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    @Override
    int getRecordsCount() {
        String selectQuery = "SELECT * FROM " + SQLiteWrapper.TABLE_RECORDS;
        SQLiteDatabase db = sqLiteWrapper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int count = cursor.getCount();
        return count;
    }
}

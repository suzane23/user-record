package com.secure.userdata.record;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataFileImpl extends UserDataBaseImpl {

    @Override
    public void init(Context context) {
        super.init(context);
        File file = new File("./Records.db");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private UserRecord fetchRecord(String line) {
        try {
            JSONObject jsonObject = new JSONObject(line);
            UserRecord record = new UserRecord();
            record.name = jsonObject.getString("name");
            record.id  = jsonObject.getString("id");
            record.time = jsonObject.getLong("time");
            return record;
        }
        catch (Exception e) {
         return  null;
        }
    }

    @Override
    void addRecordTo(UserRecord record) {
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File("./Records.db"), true);
            Writer writer = new OutputStreamWriter(fileOutputStream);
            writer.write(record.toString() + "\n");
            writer.flush();
            writer.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            System.out.println("Add record : " + record.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    int deleteRecordByName(String name) {
return 0;
    }

    @Override
    List<UserRecord> getRecordsByName(String name) {
        return null;
    }

    @Override
    List<UserRecord> getAllRecords() {
        List<UserRecord> userRecordList = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream("./Records.db");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = bufferedReader.readLine();
            while (line!=null){
                UserRecord record = fetchRecord(line);
                userRecordList.add(record);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRecordList;
    }

    @Override
    int getRecordsCount() {
        return 0;
    }

}

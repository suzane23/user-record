package com.secure.userdata;

import android.os.Bundle;

import com.secure.userdata.record.RecordFactory;
import com.secure.userdata.record.UserDataController;
import com.secure.userdata.record.UserRecord;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Integer n1 = 150;
        Integer n2 = 150;
        if(n1.equals(n2)){
            System.out.println("both numbers are equal");
        } else {
            System.out.println("both numbers are not equal");
        }

        UserDataController controller = new UserDataController(this, RecordFactory.RecordStorageType.NETWORKDB);
       UserRecord record = new UserRecord("Basooli Bhai");
        UserRecord record1 = new UserRecord("Yudhi");
        UserRecord record2 = new UserRecord("Abhimanyu");
        UserRecord record3 = new UserRecord("Anika");
        UserRecord record4 = new UserRecord("Avika");
        UserRecord record5 = new UserRecord("Divit");
        UserRecord record6 = new UserRecord("Sarla");
        UserRecord record7 = new UserRecord("Saresh");
        UserRecord record8 = new UserRecord("Shuchika");
        UserRecord record9 = new UserRecord("Ruchika");

//        controller.add(record);
//        controller.add(record1);
//        controller.add(record2);
//        controller.add(record3);
//        controller.add(record4);
//        controller.add(record5);
//        controller.add(record6);
//        controller.add(record7);
//        controller.add(record8);
//        controller.add(record9);
        controller.getAllRecords();
//       controller.deleteRecord("Shuchika");
//        controller.getRecordsByName("Avika");
        controller.getRecordsCount();
    }
}
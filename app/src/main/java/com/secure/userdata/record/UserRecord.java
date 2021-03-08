package com.secure.userdata.record;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.UUID;

public class UserRecord {

    String id;
    String name;
    Long time;

    public UserRecord() {
    }



    public UserRecord(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.time = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", this.id);
            jsonObject.put("name", this.name);
            jsonObject.put("time", this.time);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jsonObject.toString();
    }

}

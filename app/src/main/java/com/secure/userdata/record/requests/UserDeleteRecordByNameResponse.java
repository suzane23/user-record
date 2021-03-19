package com.secure.userdata.record.requests;

public class UserDeleteRecordByNameResponse extends UserBaseResponse {
    private int count;

    public UserDeleteRecordByNameResponse(boolean result, int count) {
        super(result);
        this.count = count;
    }

    public int getCount(){
        return count;
    }

}

package com.secure.userdata.record;

public class RecordFactory {
    public enum RecordStorageType {
        LOCALDB,
        FILE,
        NETWORKDB;
    }

    public static IUserData getInstance(RecordStorageType recordStorageType){
        if(recordStorageType == RecordStorageType.LOCALDB) {
            return new UserDataLocalDBImpl();
        }
        else if(recordStorageType == RecordStorageType.FILE) {
            return new UserDataFileImpl();
        }
        else if(recordStorageType == RecordStorageType.NETWORKDB){
            return new UserDataNetDBImpl();
        }
        else return null;

    }
}

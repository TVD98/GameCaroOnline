package com.example.gamecaroonline.helper;

import com.example.gamecaroonline.models.Room;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseSingleton {
    public FirebaseDatabase database;

    private FirebaseSingleton(){
        database = FirebaseDatabase.getInstance();
    }

    public static FirebaseSingleton getInstance(){
        return FirebaseSingletonHelper.INSTANCE;
    }

    public void insertRoom(Room room){
        database.getReference("room").child(room.getId()).setValue(room);
    }

    private static class FirebaseSingletonHelper{
        private static final FirebaseSingleton INSTANCE = new FirebaseSingleton();
    }
}

package com.example.dagger2demo.LocalData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dagger2demo.LocalData.PostLocalDatabase.UserPostDao;
import com.example.dagger2demo.LocalData.PostLocalDatabase.UserPosts;

@Database(entities = {LocalUser.class, UserPosts.class}, version = 3)
public abstract class LocalUserDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract UserPostDao userPostDao();
    private static LocalUserDatabase instance;
    public static LocalUserDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, LocalUserDatabase.class, "database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
package com.example.dagger2demo;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.dagger2demo.LocalData.LocalUser;
import com.example.dagger2demo.LocalData.LocalUserDatabase;

import com.example.dagger2demo.LocalData.UserDataSource;
import com.example.dagger2demo.LocalData.UserRepository;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StoredData {

    private final LocalUserDatabase localUserDatabase;
    private final UserRepository userRepository;

    @Inject
    public StoredData(Application application){
        this.localUserDatabase = LocalUserDatabase.getInstance(application);
        this.userRepository = UserRepository.getInstance(UserDataSource.getInstance(localUserDatabase.userDao()));

    }


    public LiveData<List<LocalUser>> getLocalUser() {
        return userRepository.getAllUsers();
    }


    public void deleteUser() {
        DeleteItemTask deleteItemTask = new DeleteItemTask();
        deleteItemTask.execute();
    }



    public void addNewUserToDatabase(LocalUser localUser) {
        new AddItemTask().execute(localUser);
    }




    private class AddItemTask extends AsyncTask<LocalUser, Void, Void> {

        @Override
        protected Void doInBackground(LocalUser... localUsers) {
            userRepository.InsertUser(localUsers[0]);
            return null;
        }
    }




    private class DeleteItemTask extends AsyncTask<LocalUser, Void, Void> {

        @Override
        protected Void doInBackground(LocalUser... item) {
            userRepository.DeleteAllUsers();
            return null;
        }

    }

}



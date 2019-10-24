package com.example.dagger2demo.LocalData;


import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;

public class UserRepository implements iUserDataSource{

    private iUserDataSource mLocalDataSource;
    private static UserRepository mInstance;

    public UserRepository(iUserDataSource mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }


    public static UserRepository getInstance(iUserDataSource mLocalDataSource){
        if(mInstance == null){
            mInstance = new UserRepository(mLocalDataSource);
        }
        return mInstance;
    }


    @Override
    public LiveData<LocalUser> gettingUserById(int userId) {
        return mLocalDataSource.gettingUserById(userId);
    }

    @Override
    public LiveData<List<LocalUser>> getAllUsers() {
        return mLocalDataSource.getAllUsers();
    }

    @Override
    public void InsertUser(LocalUser... users) {
        mLocalDataSource.InsertUser(users);
    }

    @Override
    public void UpdateUser(LocalUser... users) {
        mLocalDataSource.UpdateUser(users);

    }

    @Override
    public void DeleteUser(LocalUser user) {
        mLocalDataSource.DeleteUser(user);
    }

    @Override
    public void DeleteAllUsers() {
        mLocalDataSource.DeleteAllUsers();
    }
}

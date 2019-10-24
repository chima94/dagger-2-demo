package com.example.dagger2demo.LocalData;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserDataSource implements iUserDataSource {
    private UserDao userDao;
    private static  UserDataSource mInstance;

    public UserDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    public static UserDataSource getInstance(UserDao userDao){
        if(mInstance == null){
            mInstance = new UserDataSource(userDao);
        }

        return mInstance;
    }

    @Override
    public LiveData<LocalUser> gettingUserById(int userId) {
        return userDao.gettingUserById(userId);
    }

    @Override
    public LiveData<List<LocalUser>> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void InsertUser(LocalUser... users) {
        userDao.InsertUser(users);
    }

    @Override
    public void UpdateUser(LocalUser... users) {
        userDao.UpdateUser(users);
    }

    @Override
    public void DeleteUser(LocalUser user) {
        userDao.DeleteUser(user);
    }

    @Override
    public void DeleteAllUsers() {
        userDao.DeleteAllUsers();
    }
}

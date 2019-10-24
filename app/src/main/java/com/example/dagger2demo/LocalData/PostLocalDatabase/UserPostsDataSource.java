package com.example.dagger2demo.LocalData.PostLocalDatabase;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserPostsDataSource implements iUserPostDataSource {

    private UserPostDao userPostDao;
    private static  UserPostsDataSource mInstance;
    public UserPostsDataSource(UserPostDao userPostDao) {
        this.userPostDao = userPostDao;
    }

    public static UserPostsDataSource getInstance(UserPostDao userPostDao){
        if(mInstance == null){
            mInstance = new UserPostsDataSource(userPostDao);
        }

        return mInstance;
    }

    @Override
    public LiveData<UserPosts> gettingUserById(int userId) {
        return userPostDao.gettingUserById(userId);
    }

    @Override
    public LiveData<List<UserPosts>> getAllPosts() {
        return userPostDao.getAllPost();
    }

    @Override
    public void InsertUser(UserPosts... posts) {
        userPostDao.InsertPosts(posts);

    }
}

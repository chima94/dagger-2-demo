package com.example.dagger2demo.LocalData.PostLocalDatabase;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserPostsRepository  implements iUserPostDataSource{

    private iUserPostDataSource mLocalDataSource;
    private static UserPostsRepository mInstance;

    public UserPostsRepository(iUserPostDataSource mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }


    public static UserPostsRepository getInstance(iUserPostDataSource mLocalDataSource){
        if(mInstance == null){
            mInstance = new UserPostsRepository(mLocalDataSource);
        }
        return mInstance;
    }

    @Override
    public LiveData<UserPosts> gettingUserById(int userId) {
        return mLocalDataSource.gettingUserById(userId);
    }

    @Override
    public LiveData<List<UserPosts>> getAllPosts() {
       return mLocalDataSource.getAllPosts();
    }

    @Override
    public void InsertUser(UserPosts... posts) {
        mLocalDataSource.InsertUser(posts);
    }
}

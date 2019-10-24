package com.example.dagger2demo.LocalData.PostLocalDatabase;

import androidx.lifecycle.LiveData;


import java.util.List;

public interface iUserPostDataSource {

    LiveData<UserPosts> gettingUserById(int userId);

    LiveData<List<UserPosts>> getAllPosts();

    void InsertUser(UserPosts... posts);
}

package com.example.dagger2demo.LocalData.PostLocalDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dagger2demo.LocalData.LocalUser;

import java.util.List;

@Dao
public interface UserPostDao {
    @Query("Select * FROM userPosts WHERE id =:userId")
    LiveData<UserPosts> gettingUserById(int userId);

    @Query("SELECT * FROM userPosts")
    LiveData<List<UserPosts>> getAllPost();

    @Insert
    void InsertPosts(UserPosts...posts);

}

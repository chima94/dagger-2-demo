package com.example.dagger2demo.LocalData;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Query("Select * FROM LocalUser WHERE id =:userId")
    LiveData<LocalUser> gettingUserById(int userId);

    @Query("SELECT * FROM LocalUser")
    LiveData<List<LocalUser>> getAllUsers();

    @Insert
    void InsertUser(LocalUser...users);

    @Update
    void UpdateUser(LocalUser...users);

    @Delete
    void DeleteUser(LocalUser user);

    @Query("DELETE FROM LocalUser")
    void DeleteAllUsers();
}

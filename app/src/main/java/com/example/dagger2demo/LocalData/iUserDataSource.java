package com.example.dagger2demo.LocalData;


import androidx.lifecycle.LiveData;



import java.util.List;

import io.reactivex.Flowable;

public interface iUserDataSource {
    LiveData<LocalUser> gettingUserById(int userId);
    LiveData<List<LocalUser>> getAllUsers();
    void InsertUser(LocalUser... users);
    void UpdateUser(LocalUser... users);
    void DeleteUser(LocalUser user);
    void DeleteAllUsers();
}

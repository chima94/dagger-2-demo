package com.example.dagger2demo.ui.Main.Profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dagger2demo.LocalData.LocalUser;
import com.example.dagger2demo.StoredData;

import java.util.List;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";
    private final StoredData storedData;

    @Inject
    public ProfileViewModel(StoredData storedData) {
        Log.d(TAG, "ProfileViewModel: Profile ViewModel Created........");
        this.storedData = storedData;
    }

    public LiveData<List<LocalUser>> getUserDetail(){
        return storedData.getLocalUser();
    }
}

package com.example.dagger2demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.dagger2demo.LocalData.LocalUser;
import com.example.dagger2demo.ui.Auth.AuthActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity  extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public StoredData storedData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObserver();

    }

    private void subscribeObserver(){
        storedData.getLocalUser().observe(this, new Observer<List<LocalUser>>() {
            @Override
            public void onChanged(List<LocalUser> localUsers) {
                if(localUsers.isEmpty()){
                    loginScreen();
                }
            }
        });
    }

    private void loginScreen(){
        Intent intent  = new Intent(this,AuthActivity.class);
        startActivity(intent);
        finish();
    }
}

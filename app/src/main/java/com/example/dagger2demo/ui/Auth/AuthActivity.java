package com.example.dagger2demo.ui.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.dagger2demo.LocalData.LocalUser;
import com.example.dagger2demo.Model.User;
import com.example.dagger2demo.R;
import com.example.dagger2demo.StoredData;
import com.example.dagger2demo.ViewModels.ViewModelProviderFactory;
import com.example.dagger2demo.ui.Main.MainActivity;


import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AuthActivity";
    private AuthViewModel authViewModel;
    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    ImageView imageView;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        editText = findViewById(R.id.user_id_input);
        authViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AuthViewModel.class);
        imageView = findViewById(R.id.login_logo);
        progressBar = findViewById(R.id.progress_bar);
        setLogo();
        findViewById(R.id.login_button).setOnClickListener(this);
        observe();
    }


    //data from database function
    private void observe() {
        authViewModel.getUserFromDatabase().observe(this, new Observer<List<LocalUser>>() {
            @Override
            public void onChanged(List<LocalUser> user_data) {
                if(!user_data.isEmpty()){
                    toMainActivity();
                }
            }
        });
    }



    //logo function
    private void setLogo(){
        requestManager.load(logo).into(imageView);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:
                login();
                break;
        }

    }


    //progress bar function
    private void showProgressBar(Boolean isShowing){
        if(isShowing){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    //login function
    private void login() {
        if(TextUtils.isEmpty(editText.getText().toString())){
            return;
        }
        authViewModel.AuthenticationWithId(Integer.parseInt(editText.getText().toString()));
        authViewModel.getUsers().observe(this, new Observer<AuthResources<User>>() {
            @Override
            public void onChanged(AuthResources<User> userAuthResources) {
                if(userAuthResources != null){
                    switch (userAuthResources.status){
                        case LOADING:
                            showProgressBar(true);
                            break;
                        case AUTHENTICATED:
                            showProgressBar(false);
                            LocalUser localUser = new LocalUser(editText.getText().toString(),userAuthResources.data.getUsername(), userAuthResources.data.getEmail(), userAuthResources.data.getWebsite());
                            authViewModel.addToDatabase(localUser);
                            break;
                        case ERROR:
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: unable to sign u in, are u sure u inputed between 1 to 10?"+ userAuthResources.message);
                            break;
                        case NOT_AUTHENTICATED:
                            showProgressBar(false);
                            break;

                    }
                }
            }
        });
    }

    private void toMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

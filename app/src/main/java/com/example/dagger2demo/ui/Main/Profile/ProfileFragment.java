package com.example.dagger2demo.ui.Main.Profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dagger2demo.LocalData.LocalUser;
import com.example.dagger2demo.R;
import com.example.dagger2demo.ViewModels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";
    private ProfileViewModel profileViewModel;
    private TextView username, email, website;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        username = view.findViewById(R.id.username_textView);
        email = view.findViewById(R.id.email_textView);
        website = view.findViewById(R.id.website_textView);
        profileViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(ProfileViewModel.class);
        subcribeObserver();
    }

    private void subcribeObserver(){
        profileViewModel.getUserDetail().removeObservers(getViewLifecycleOwner());
        profileViewModel.getUserDetail().observe(getViewLifecycleOwner(), new Observer<List<LocalUser>>() {
            @Override
            public void onChanged(List<LocalUser> localUsers) {
                if(!localUsers.isEmpty()){
                    setUsertextView(localUsers);
                }
            }
        });
    }

    public void setUsertextView(List<LocalUser> user){
        username.setText(user.get(0).getUsername());
        email.setText(user.get(0).getEmail());
        website.setText(user.get(0).getWebsite());
        Log.d(TAG, "setUsertextView: "+user.get(0).getUserId());
    }
}

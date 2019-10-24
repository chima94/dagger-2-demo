package com.example.dagger2demo.Di.Auth;

import androidx.lifecycle.ViewModel;

import com.example.dagger2demo.Di.ViewModelKey;
import com.example.dagger2demo.ui.Auth.AuthViewModel;
import com.example.dagger2demo.ui.Main.Post.PostViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModule(AuthViewModel authViewModel);


}

package com.example.dagger2demo.Di.Main;


import androidx.lifecycle.ViewModel;

import com.example.dagger2demo.Di.ViewModelKey;
import com.example.dagger2demo.ui.Main.Post.PostViewModel;
import com.example.dagger2demo.ui.Main.Profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ProfileViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModule(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    public abstract ViewModel bindPostViewModule(PostViewModel authViewModel);
}

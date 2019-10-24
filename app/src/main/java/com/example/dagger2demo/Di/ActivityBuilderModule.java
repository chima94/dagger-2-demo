package com.example.dagger2demo.Di;


import com.example.dagger2demo.Di.Auth.AuthModule;
import com.example.dagger2demo.Di.Auth.AuthScope;
import com.example.dagger2demo.Di.Auth.AuthViewModelModule;
import com.example.dagger2demo.Di.Main.MainFragmentBuildersModule;
import com.example.dagger2demo.Di.Main.MainModule;
import com.example.dagger2demo.Di.Main.MainScope;
import com.example.dagger2demo.Di.Main.ProfileViewModelModule;
import com.example.dagger2demo.ui.Auth.AuthActivity;
import com.example.dagger2demo.ui.Main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelModule.class, AuthModule.class}
    )
    abstract AuthActivity authActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, ProfileViewModelModule.class, MainModule.class}
    )
    abstract MainActivity mainActivity();

}

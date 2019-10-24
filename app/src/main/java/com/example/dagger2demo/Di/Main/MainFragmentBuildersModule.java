package com.example.dagger2demo.Di.Main;


import com.example.dagger2demo.ui.Main.Post.PostFragment;
import com.example.dagger2demo.ui.Main.Profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract ProfileFragment profileFragment();

    @ContributesAndroidInjector
    abstract PostFragment postFragment();
}

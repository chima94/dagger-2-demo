package com.example.dagger2demo.Di.Main;

import com.example.dagger2demo.Network.Main.MainApi;
import com.example.dagger2demo.ui.Main.Post.PostRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static PostRecyclerAdapter providePostRecycleAdapter(){
        return new PostRecyclerAdapter();
    }


    @MainScope
    @Provides
    static MainApi providesAuthApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }
}

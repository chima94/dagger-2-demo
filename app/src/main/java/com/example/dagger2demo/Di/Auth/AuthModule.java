package com.example.dagger2demo.Di.Auth;

import com.example.dagger2demo.Network.Auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @AuthScope
    @Provides
    static AuthApi providesAuthApi(Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }
}

package com.example.dagger2demo.ui.Auth;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.dagger2demo.LocalData.LocalUser;
import com.example.dagger2demo.LocalData.UserRepository;
import com.example.dagger2demo.Model.User;
import com.example.dagger2demo.Network.Auth.AuthApi;
import com.example.dagger2demo.StoredData;

import java.util.List;

import javax.inject.Inject;


import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private AuthApi authApi;
    private final StoredData storedData;
    private UserRepository userRepository;
    private MediatorLiveData<AuthResources<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi, StoredData storedData) {
        this.authApi = authApi;
        this.storedData = storedData;
        Log.d(TAG, "AuthViewModel: View module is created...");

    }

    public void AuthenticationWithId(int userId) {
        authUser.setValue(AuthResources.loading((User)null));

        final LiveData<AuthResources<User>> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                        .map(new Function<User, AuthResources<User>>() {
                            @Override
                            public AuthResources<User> apply(User user) throws Exception {
                                if(user.getId() == -1){
                                    return AuthResources.error("Could not retrieve user", (User)null);
                                }
                                return AuthResources.authenticated(user);
                            }
                        })
                .subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, new Observer<AuthResources<User>>() {
            @Override
            public void onChanged(AuthResources<User> user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }




    public LiveData<AuthResources<User>> getUsers(){
        return authUser;
    }


    public void addToDatabase(LocalUser localUser){
        storedData.addNewUserToDatabase(localUser);
    }



    public LiveData<List<LocalUser>> getUserFromDatabase(){
        return storedData.getLocalUser();
    }




}

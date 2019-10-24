package com.example.dagger2demo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.dagger2demo.LocalData.LocalUserDatabase;
import com.example.dagger2demo.LocalData.PostLocalDatabase.UserPosts;
import com.example.dagger2demo.LocalData.PostLocalDatabase.UserPostsDataSource;
import com.example.dagger2demo.LocalData.PostLocalDatabase.UserPostsRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StoredPosts {
    private final LocalUserDatabase localPostDatabase;
    private final UserPostsRepository userPostsRepository;

    @Inject
    public StoredPosts(Application application){
        this.localPostDatabase = LocalUserDatabase.getInstance(application);
        this.userPostsRepository = UserPostsRepository.getInstance(UserPostsDataSource.getInstance(localPostDatabase.userPostDao()));
    }

    public LiveData<List<UserPosts>> getLocalPosts() {
        return userPostsRepository.getAllPosts();
    }


    public void addNewUserToDatabase(UserPosts posts) {
        new AddItemTask().execute(posts);
    }




    private class AddItemTask extends AsyncTask<UserPosts, Void, Void> {

        @Override
        protected Void doInBackground(UserPosts... posts) {
            userPostsRepository.InsertUser(posts[0]);
            return null;
        }
    }
}

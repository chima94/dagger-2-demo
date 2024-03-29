package com.example.dagger2demo.ui.Main.Post;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.dagger2demo.LocalData.LocalUser;
import com.example.dagger2demo.LocalData.PostLocalDatabase.UserPosts;
import com.example.dagger2demo.Model.Post;
import com.example.dagger2demo.Network.Main.MainApi;
import com.example.dagger2demo.StoredData;
import com.example.dagger2demo.StoredPosts;
import com.example.dagger2demo.ui.Main.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {
    private static final String TAG = "PostViewModel";
    private final StoredData storedData;
    private final StoredPosts storedPosts;
    private MainApi mainApi;

    private MediatorLiveData<Resource<List<Post>>> posts;

    @Inject
    public PostViewModel(StoredData storedData, StoredPosts storedPosts, MainApi mainApi) {
        this.storedData = storedData;
        this.storedPosts = storedPosts;
        this.mainApi = mainApi;
        Log.d(TAG, "PostViewModel: PostViewModel Created......");
    }

    public LiveData<Resource<List<Post>>> observePosts(int i){
        if(posts == null){
            posts = new MediatorLiveData<>();
            posts.setValue(Resource.loading((List<Post>)null));
            final LiveData<Resource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPosts(i)
                    .onErrorReturn(new Function<Throwable, List<Post>>() {
                        @Override
                        public List<Post> apply(Throwable throwable) throws Exception {
                            Log.e(TAG, "apply: "+ throwable );
                            Post post = new Post();
                            post.setId(-1);
                            ArrayList<Post> posts = new ArrayList<>();
                            posts.add(post);
                            return posts;

                        }
                    })
                    .map(new Function<List<Post>, Resource<List<Post>>>() {
                        @Override
                        public Resource<List<Post>> apply(List<Post> posts) throws Exception {
                            if(posts.size() > 0){
                                if(posts.get(0).getId() == -1){
                                    return Resource.error("something went wrong", null);
                                }
                            }
                            return Resource.success(posts);
                        }
                    })
                    .subscribeOn(Schedulers.io())
            );
            posts.addSource(source, new Observer<Resource<List<Post>>>() {
                @Override
                public void onChanged(Resource<List<Post>> listResource) {
                    posts.setValue(listResource);
                    posts.removeSource(source);
                }
            });
        }
        return posts;
    }

    public LiveData<List<LocalUser>>getId(){
        return storedData.getLocalUser();
    }

}

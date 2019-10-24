package com.example.dagger2demo.ui.Main.Post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dagger2demo.LocalData.LocalUser;
import com.example.dagger2demo.Model.Post;
import com.example.dagger2demo.R;
import com.example.dagger2demo.ViewModels.ViewModelProviderFactory;
import com.example.dagger2demo.ui.Main.Resource;
import com.example.dagger2demo.util.VerticalSpaceItemDecoration;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostFragment extends DaggerFragment {

    private static final String TAG = "PostFragment";

    private  PostViewModel postViewModel;
    private RecyclerView recyclerView;
    private String userId, id;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    PostRecyclerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycleView);
        postViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(PostViewModel.class);
        InitRecyclerView();
        subscribObserver();

    }

    private void subscribObserver(){
        postViewModel.getId().removeObservers(getViewLifecycleOwner());
        postViewModel.getId().observe(getViewLifecycleOwner(), new Observer<List<LocalUser>>() {
            @Override
            public void onChanged(List<LocalUser> localUsers) {
                if(!localUsers.isEmpty()){
                    subcribeOnObservePost(Integer.parseInt(localUsers.get(0).getUserId()));
                }
            }
        });

    }

    private void subcribeOnObservePost(int id){
        Log.d(TAG, "subcribeOnObservePost: "+id);
        postViewModel.observePosts(id).observe(this, new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if(listResource != null){
                    switch (listResource.status){
                        case LOADING:
                            Log.d(TAG, "onChanged: LOADING.....");
                            break;
                        case SUCCESS:
                            Log.d(TAG, "onChanged: showing post.....");
                            adapter.setPosts(listResource.data);
                            break;
                        case ERROR:
                            Log.d(TAG, "onChanged: "+listResource.message);
                            break;
                    }

                }
            }
        });
    }


    private void InitRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpaceItemDecoration itemDecoration = new VerticalSpaceItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
    }
}

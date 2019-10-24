package com.example.dagger2demo.Di;

import android.app.Application;

import com.example.dagger2demo.BaseApplication;
import com.example.dagger2demo.StoredData;
import com.example.dagger2demo.StoredPosts;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(
        modules = {AndroidInjectionModule.class,
                    ActivityBuilderModule.class,
                    AppModule.class,
                    ViewModelFactoryModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    StoredData storedData();
    StoredPosts storedPosts();
    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}

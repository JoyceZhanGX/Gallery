package com.example.gallery;

import android.app.Application;
import com.example.gallery.mvvm.Repository;

public class AppApplication extends Application {

    public static Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();

        repository = Repository.getInstance(this);
    }
}

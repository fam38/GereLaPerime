package com.example.martin.gerelaperime;

import android.app.Application;

import SqlLite.object.StorageService;
import SqlLite.object.StorageServiceImpl;

/**
 * Created by Martin on 24/04/2016.
 */
public class MyApplication extends Application {
    private StorageService storageService;

    @Override
    public void onCreate() {
        super.onCreate();
        storageService = new StorageServiceImpl(this);
    }

    public StorageService getStorageService() {
        return storageService;
    }
}

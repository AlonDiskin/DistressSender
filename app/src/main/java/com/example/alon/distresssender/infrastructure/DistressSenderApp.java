package com.example.alon.distresssender.infrastructure;

import android.app.Application;

import com.example.alon.distresssender.infrastructure.di.component.AppComponent;
import com.example.alon.distresssender.infrastructure.di.component.DaggerAppComponent;
import com.example.alon.distresssender.infrastructure.di.module.AppModule;


public class DistressSenderApp extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}

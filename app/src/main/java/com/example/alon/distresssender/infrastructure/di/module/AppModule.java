package com.example.alon.distresssender.infrastructure.di.module;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import com.example.alon.distresssender.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by alon on 6/16/17.
 */
@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    FirebaseDatabase providesFirebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    @Singleton
    @Provides
    DatabaseReference provideDatabaseReference(FirebaseDatabase database, Application application) {
        return database.getReference(application.getString(R.string.key_distress_calls_db_reference));
    }

    @Singleton
    @Provides
    FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Singleton
    @Provides
    GoogleApiClient rovideGoogleApiClient(Application application) {
        return new GoogleApiClient.Builder(application)
                .addApi(LocationServices.API)
                .build();
    }

    @Singleton
    @Provides
    ConnectivityManager provideConnectivityManager(Application application) {
        return (ConnectivityManager)application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}

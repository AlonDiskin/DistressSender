package com.example.alon.distresssender.infrastructure.di.module;

import com.example.alon.distresssender.domain.core.model.DistressCallSender;
import com.example.alon.distresssender.domain.core.model.DistressCallSenderImpl;
import com.example.alon.distresssender.domain.core.service.LocationService;
import com.example.alon.distresssender.domain.core.service.LocationServiceImpl;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by alon on 6/16/17.
 */
@Module
public class DistressCallSenderModule {

    @Singleton
    @Provides
    LocationService provideLocationService(GoogleApiClient googleApiClient) {
        return new LocationServiceImpl(googleApiClient);
    }

    @Singleton
    @Provides
    DistressCallSender provideDistressCallSender(DatabaseReference dbReference, FirebaseAuth firebaseAuth) {
        return new DistressCallSenderImpl(dbReference,firebaseAuth);
    }
}

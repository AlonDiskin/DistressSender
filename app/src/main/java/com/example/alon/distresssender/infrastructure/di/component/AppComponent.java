package com.example.alon.distresssender.infrastructure.di.component;

import android.net.ConnectivityManager;

import com.example.alon.distresssender.domain.core.model.DistressCallSender;
import com.example.alon.distresssender.domain.core.service.LocationService;
import com.example.alon.distresssender.infrastructure.di.module.AppModule;
import com.example.alon.distresssender.infrastructure.di.module.DistressCallSenderModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alon on 6/16/17.
 */
@Singleton
@Component(modules = {AppModule.class, DistressCallSenderModule.class})
public interface AppComponent {

    DistressCallSender distressCallSender();

    LocationService locationService();

    ConnectivityManager connectivityManager();
}

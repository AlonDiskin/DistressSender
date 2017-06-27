package com.example.alon.distresssender.infrastructure.di.component;

import com.example.alon.distresssender.infrastructure.di.module.CallSendingModule;
import com.example.alon.distresssender.infrastructure.di.scope.ActivityScope;
import com.example.alon.distresssender.presentation.call_sending.CallSendingActivity;

import dagger.Component;

/**
 * Created by alon on 6/16/17.
 */
@ActivityScope
@Component(modules = CallSendingModule.class,dependencies = AppComponent.class)
public interface CallSendingComponent {

    void inject(CallSendingActivity activity);
}

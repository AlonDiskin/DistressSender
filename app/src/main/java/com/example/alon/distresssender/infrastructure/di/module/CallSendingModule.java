package com.example.alon.distresssender.infrastructure.di.module;

import android.net.ConnectivityManager;

import com.example.alon.distresssender.domain.application_services.call_sending.facade.CallSendingDomain;
import com.example.alon.distresssender.domain.application_services.call_sending.facade.CallSendingDomainImpl;
import com.example.alon.distresssender.domain.application_services.call_sending.use_case.SendDistressCall;
import com.example.alon.distresssender.domain.application_services.call_sending.use_case.SendDistressCallImpl;
import com.example.alon.distresssender.domain.core.model.DistressCallSender;
import com.example.alon.distresssender.domain.core.service.LocationService;
import com.example.alon.distresssender.infrastructure.di.scope.ActivityScope;
import com.example.alon.distresssender.presentation.call_sending.CallSendingContract;
import com.example.alon.distresssender.presentation.call_sending.CallSendingPresenter;

import dagger.Module;
import dagger.Provides;


/**
 * Created by alon on 6/16/17.
 */
@Module
public class CallSendingModule {

    private CallSendingContract.View mView;

    public CallSendingModule(CallSendingContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    CallSendingContract.View provideView() {
        return mView;
    }

    @ActivityScope
    @Provides
    SendDistressCall provideSendDistressCall(DistressCallSender distressCallSender,LocationService locationService,
                                             ConnectivityManager connectivityManager) {
        return new SendDistressCallImpl(distressCallSender,locationService,connectivityManager);
    }

    @ActivityScope
    @Provides
    CallSendingDomain provideDomain(SendDistressCall sendDistressCall) {
        return new CallSendingDomainImpl(sendDistressCall);
    }

    @ActivityScope
    @Provides
    CallSendingContract.Presenter providePresenter(CallSendingContract.View view,CallSendingDomain domain) {
        return new CallSendingPresenter(view,domain);
    }
}

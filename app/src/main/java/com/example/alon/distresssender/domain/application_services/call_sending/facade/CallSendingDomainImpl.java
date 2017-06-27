package com.example.alon.distresssender.domain.application_services.call_sending.facade;

import com.example.alon.distresssender.domain.application_services.call_sending.use_case.SendDistressCall;
import com.example.alon.distresssender.domain.application_services.common.Failure;
import com.example.alon.distresssender.domain.application_services.common.Success;

import javax.inject.Inject;


/**
 * {@link CallSendingDomain} implementation.
 */

public class CallSendingDomainImpl implements CallSendingDomain {

    private SendDistressCall mSendDistressCall;

    @Inject
    public CallSendingDomainImpl(SendDistressCall sendDistressCall) {
        mSendDistressCall = sendDistressCall;
    }

    @Override
    public void sendDistressCall(Success<Boolean> success, Failure failure) {
        mSendDistressCall.sendCall(success,failure);
    }
}

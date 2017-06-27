package com.example.alon.distresssender.presentation.call_sending;

import com.example.alon.distresssender.domain.application_services.call_sending.facade.CallSendingDomain;
import com.example.alon.distresssender.domain.core.value.LocationServiceException;

import javax.inject.Inject;

/**
 * This class act as the presenter in the call sending feature.
 * Providing a bridge between the view and domain layers, handle
 * events from and send data to the view by accessing the domain.
 */

public class CallSendingPresenter implements CallSendingContract.Presenter {

    private CallSendingContract.View mView;
    private CallSendingDomain mDomain;

    @Inject
    public CallSendingPresenter(CallSendingContract.View view,CallSendingDomain domain) {
        mView = view;
        mDomain = domain;
    }

    @Override
    public void onViewDestroy() {
        // detach the view from this presenter.
        mView = null;
    }

    @Override
    public void sendDistressCall() {
        // ask the domain to send awy the given call
        mDomain.sendDistressCall(this::handleSendingDistressCallSuccess,
                this::handleSendingDistressCallFailure);
    }

    /**
     * Handle call sending success by directing the view...
     *
     * @param result always a true value
     */
    private void handleSendingDistressCallSuccess(boolean result) {
        if (mView != null) {
            mView.notifyDistressCallSent();
        }
    }

    /**
     * Handle call sending error scenario
     *
     * @param throwable error.
     */
    private void handleSendingDistressCallFailure(Throwable throwable) {
        throwable.printStackTrace();
        if (mView != null) {
            if (throwable instanceof LocationServiceException) {
                LocationServiceException locationServiceException =
                        (LocationServiceException) throwable;
                mView.showLocationSettingsResolution(locationServiceException.getStatus());
            }
        }
    }
}

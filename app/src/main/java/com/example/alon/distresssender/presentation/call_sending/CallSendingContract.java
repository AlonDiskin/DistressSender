package com.example.alon.distresssender.presentation.call_sending;

import com.google.android.gms.common.api.Status;

/**
 * Call sending feature MVP contract.
 */

public interface CallSendingContract {

    /**
     * Call sending feature view interface
     */
    interface View {

        /**
         * Notifies the user that a distress call
         * was sent successfully.
         */
        void notifyDistressCallSent();

        void showLocationSettingsResolution(Status status);
    }

    /**
     * Call sending feature presenter interface
     */
    interface Presenter {

        /**
         * Called by the {@link View} when it is about to be destroyed.
         */
        void onViewDestroy();

        /**
         * Send a distress call.
         *
         */
        void sendDistressCall();
    }
}

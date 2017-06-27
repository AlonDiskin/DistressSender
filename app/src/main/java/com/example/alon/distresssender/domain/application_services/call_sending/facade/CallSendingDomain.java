package com.example.alon.distresssender.domain.application_services.call_sending.facade;

import com.example.alon.distresssender.domain.application_services.common.Failure;
import com.example.alon.distresssender.domain.application_services.common.Success;

/**
 * Call sending feature domain facade.
 */

public interface CallSendingDomain {

    /**
     * Send a distress call.
     *
     * @param success sending call success callback.
     * @param failure sending call failure callback.
     */
    void sendDistressCall(Success<Boolean> success, Failure failure);
}

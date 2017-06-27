package com.example.alon.distresssender.domain.application_services.call_sending.use_case;

import com.example.alon.distresssender.domain.application_services.common.Failure;
import com.example.alon.distresssender.domain.application_services.common.Success;

/**
 * Send a distress call use case.
 */

public interface SendDistressCall {

    /**
     * Send awy the given {@code call}.
     *
     * @param success use case success callback.
     * @param failure use case failure callback.
     */
    void sendCall(Success<Boolean> success, Failure failure);
}

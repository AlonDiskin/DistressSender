package com.example.alon.distresssender.domain.application_services.common;

/**
 * Use case success callback interface.
 */

public interface Success<R> {

    /**
     * Called if use case task was completed successfully.
     *
     * @param result use case result data type.
     */
    void onSuccess(R result);
}

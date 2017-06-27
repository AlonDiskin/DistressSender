package com.example.alon.distresssender.domain.application_services.common;

/**
 * Use case failure callback interface.
 */

public interface Failure {

    /**
     * Called when use case task execution failed.
     *
     * @param throwable failure.
     */
    void onFailure(Throwable throwable);
}

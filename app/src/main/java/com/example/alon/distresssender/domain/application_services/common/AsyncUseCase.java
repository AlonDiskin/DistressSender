package com.example.alon.distresssender.domain.application_services.common;

/**
 * Application services use case interface. Represents a application
 * services layer use case, that results in the execution of an async operation
 *
 * @param <T> input parameter class for this use case task execution.
 * @param <R> result class type of this use case task execution.
 */
public interface AsyncUseCase<T,R> {

    /**
     *
     * @param param
     * @param success
     * @param failure
     */
    void executeUseCase(T param, Success<R> success, Failure failure);

    /**
     * Cancel the current use case task execution.
     */
    void cancel();
}

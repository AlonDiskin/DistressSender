package com.example.alon.distresssender.domain.application_services.common;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * {@link AsyncUseCase} abstract implementation class for executing
 * async use cases by using the RxJava Api
 */
public abstract class RxUseCase<T,R> implements AsyncUseCase<T,R> {

    private Disposable mDisposable;

    @Override
    public void executeUseCase(T param, Success<R> success, Failure failure) {
        mDisposable = buildSingleObservable(param)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> handleSuccess(result,success),
                        throwable -> handleFailure(throwable,failure));
    }

    @Override
    public void cancel() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    /**
     * Build the unique {@link Single} execution stream
     * for this use case task.
     *
     * @param param use case input param
     * @return {@link Single} observable stream.
     */
    protected abstract Single<R> buildSingleObservable(T param);

    /**
     * Handles use case success by invoking {@code success} callback.
     *
     * @param result use case result
     * @param success {@link Success} callback.
     */
    private void handleSuccess(R result,Success<R> success) {
        if (success != null) {
            success.onSuccess(result);
        }
    }

    /**
     * Handles use case failure by invoking {@code failure} callback
     *
     * @param throwable failure error.
     * @param failure {@link Failure} callback.
     */
    private void handleFailure(Throwable throwable,Failure failure) {
        if (failure != null) {
            failure.onFailure(throwable);
        }
    }
}

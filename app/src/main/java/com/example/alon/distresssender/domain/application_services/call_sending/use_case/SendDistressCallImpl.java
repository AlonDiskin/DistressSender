package com.example.alon.distresssender.domain.application_services.call_sending.use_case;

import android.accounts.NetworkErrorException;
import android.net.ConnectivityManager;

import com.example.alon.distresssender.domain.application_services.common.RxUseCase;
import com.example.alon.distresssender.domain.application_services.common.Failure;
import com.example.alon.distresssender.domain.application_services.common.Success;
import com.example.alon.distresssender.domain.core.model.DistressCallSender;
import com.example.alon.distresssender.domain.core.service.LocationService;
import com.example.alon.distresssender.domain.core.value.GeoLocation;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;


/**
 * {@link SendDistressCall} implementation class.
 */

public class SendDistressCallImpl extends RxUseCase<Void,Boolean> implements SendDistressCall {

    private DistressCallSender mDistressCallSender;
    private LocationService mLocationService;
    private ConnectivityManager mConnectivityManager;

    @Inject
    public SendDistressCallImpl(DistressCallSender distressCallSender,
                                LocationService locationService,
                                ConnectivityManager connectivityManager) {
        mDistressCallSender = distressCallSender;
        mLocationService = locationService;
        mConnectivityManager = connectivityManager;
    }

    @Override
    public void sendCall(Success<Boolean> success, Failure failure) {
        super.executeUseCase(null,success,failure);
    }

    @Override
    protected Single<Boolean> buildSingleObservable(Void param) {
        return getGeoLocation()
                .flatMap(this::checkNetwork)
                .flatMap(this::sendDistressCall)
                .subscribeOn(Schedulers.io());
    }

    private Single<GeoLocation> getGeoLocation() {
        return Single.create(e -> {
            while (mLocationService.isConnecting()) {
                Thread.sleep(100);
            }

            mLocationService.getCurrentLocation(new LocationService.LocationCallback() {
                @Override
                public void onLocationReceived(GeoLocation location) {
                    e.onSuccess(location);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    e.onError(throwable);
                }
            });
        });
    }

    private Single<GeoLocation> checkNetwork(GeoLocation location) {
        return Single.fromCallable(() -> {
            boolean isWifiConn = mConnectivityManager.getNetworkInfo(
                    ConnectivityManager.TYPE_WIFI).isConnected();
//            boolean isMobileConn = mConnectivityManager.getNetworkInfo(
//                    ConnectivityManager.TYPE_MOBILE).isConnected();
            if(!isWifiConn) {
                throw new NetworkErrorException("no internet connection");
            }
            return location;
        });
    }

    private Single<Boolean> sendDistressCall(GeoLocation location) {
        return Single.fromCallable(() -> {
            mDistressCallSender.sendCall(location);
            return true;
        });
    }
}

package com.example.alon.distresssender.domain.core.service;

import com.example.alon.distresssender.domain.core.value.GeoLocation;

/**
 * Domain service providing user location services.
 */

public interface LocationService {

    interface LocationCallback {

        void onLocationReceived(GeoLocation location);

        void onFailure(Throwable throwable);
    }

    void getCurrentLocation(LocationCallback callback);

    boolean isConnecting();
}

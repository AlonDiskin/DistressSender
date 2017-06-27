package com.example.alon.distresssender.domain.core.model;

import com.example.alon.distresssender.domain.core.value.GeoLocation;


/**
 * Domain model.
 */

public interface DistressCallSender {

    /**
     *
     * @param location
     */
    void sendCall(GeoLocation location);
}

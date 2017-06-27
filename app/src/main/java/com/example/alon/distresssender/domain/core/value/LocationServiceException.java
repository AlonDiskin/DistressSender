package com.example.alon.distresssender.domain.core.value;

import com.google.android.gms.common.api.Status;

/**
 * Created by alon on 6/17/17.
 */

public class LocationServiceException extends Exception {

    private String message;
    private Status status;

    public LocationServiceException(Status status) {
        super("LocationEntry settings resolution required");
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}

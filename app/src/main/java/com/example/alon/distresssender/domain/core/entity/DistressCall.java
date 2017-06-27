package com.example.alon.distresssender.domain.core.entity;

import com.example.alon.distresssender.domain.core.value.GeoLocation;

/**
 * Domain entity class holding info about
 * a distress call made by app user.
 */

public class DistressCall {

    private User user;
    private GeoLocation location;
    private long timeStamp;

    public DistressCall() {
    }

    public DistressCall(User user, GeoLocation location, long timeStamp) {
        this.user = user;
        this.location = location;
        this.timeStamp = timeStamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}

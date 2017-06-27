package com.example.alon.distresssender.domain.core.model;

import com.example.alon.distresssender.domain.core.entity.DistressCall;
import com.example.alon.distresssender.domain.core.entity.User;
import com.example.alon.distresssender.domain.core.value.GeoLocation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * {@link DistressCallSender} implementation class. Sends {@link DistressCall}s
 * to the application firebase real time database.
 */

public class DistressCallSenderImpl implements DistressCallSender{

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Inject
    public DistressCallSenderImpl(DatabaseReference databaseReference,FirebaseAuth firebaseAuth) {
        mDatabase = databaseReference;
        mAuth = firebaseAuth;
    }

    @Override
    public void sendCall(GeoLocation location) {
        FirebaseUser user = mAuth.getCurrentUser();
        DistressCall call = new DistressCall(new User(user.getDisplayName(),
                user.getPhotoUrl().toString()),location, Calendar.getInstance().getTimeInMillis());

        mDatabase.push().setValue(call);
    }
}

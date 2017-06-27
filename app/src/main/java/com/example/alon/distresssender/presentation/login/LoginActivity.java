package com.example.alon.distresssender.presentation.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alon.distresssender.presentation.call_sending.CallSendingActivity;
import com.example.alon.distresssender.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private static final String LOG_TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuth.AuthStateListener mAuthStateListener = firebaseAuth -> {
        if (firebaseAuth.getCurrentUser() != null) {
            // user already logged in, start call sending
            // activity with user info, finish current activity.
            FirebaseUser user = firebaseAuth.getCurrentUser();

            Intent intent = new Intent(LoginActivity.this,CallSendingActivity.class);

            intent.putExtra(getString(R.string.key_user_name),user.getDisplayName());
            intent.putExtra(getString(R.string.key_user_photo),user.getPhotoUrl());
            startActivity(intent);
            finish();
        } else {
            // user not logged/signed in. launch
            // firebase login/signIn activity and process results.
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()))
                            .build(),
                    RC_SIGN_IN);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            FirebaseAuth.getInstance().addAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == ResultCodes.OK) {
                startActivity(new Intent(this,CallSendingActivity.class));
            }
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        if (mAuthStateListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
        }
        super.onDestroy();
    }
}

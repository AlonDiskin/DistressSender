package com.example.alon.distresssender.presentation.call_sending;

import android.content.Intent;
import android.content.IntentSender;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.alon.distresssender.R;
import com.example.alon.distresssender.databinding.ActivityCallSendingBinding;
import com.example.alon.distresssender.domain.core.entity.User;
import com.example.alon.distresssender.infrastructure.DistressSenderApp;
import com.example.alon.distresssender.infrastructure.di.component.DaggerCallSendingComponent;
import com.example.alon.distresssender.infrastructure.di.module.CallSendingModule;
import com.example.alon.distresssender.presentation.login.LoginActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.common.api.Status;

import javax.inject.Inject;


/**
 * This activity act as the view in Call sending feature, implementing
 * {@link CallSendingContract.View}. Primarily allowing the user to send
 * a distress call.
 */
public class CallSendingActivity extends AppCompatActivity implements CallSendingContract.View,
        LogoutDialog.DialogActionsListener {

    private static final String LOG_TAG = CallSendingActivity.class.getSimpleName();
    private static final int REQUEST_CHECK_LOCATION_SETTINGS = 600;
    private ActivityCallSendingBinding mLayoutBinding;

    @Inject
    CallSendingContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inject presenter for this activity
        injectPresenter();

        // set activity layout and its binding
        mLayoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_call_sending);

        // set toolbar
        setSupportActionBar(mLayoutBinding.toolbar);

        // set user info in ui layout
        User user = new User();

        user.setName(getIntent().getStringExtra(getString(R.string.key_user_name)));
        user.setPhotoUrl(getIntent().getStringExtra(getString(R.string.key_user_photo)));
        mLayoutBinding.setUser(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_call_sending, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;

            case R.id.action_logout:
                LogoutDialog dialog = new LogoutDialog();
                dialog.show(getSupportFragmentManager(), "logout_dialog");
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CHECK_LOCATION_SETTINGS:
                    mPresenter.sendDistressCall();
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        // notify the presenter that this view
        // is about to be destroyed.
        mPresenter.onViewDestroy();
        super.onDestroy();
    }

    @Override
    public void onLogoutConfirmed() {
        // logout using firebase api
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(task -> {
                    // user is now signed out
                    startActivity(new Intent(CallSendingActivity.this, LoginActivity.class));
                    finish();
                });
    }

    @Override
    public void notifyDistressCallSent() {
        Log.d(LOG_TAG,"distress call was sent");
        Toast.makeText(this, "Distress call sent!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLocationSettingsResolution(Status status) {
        try {
            status.startResolutionForResult(this, REQUEST_CHECK_LOCATION_SETTINGS);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Injects the presenter for this view.
     */
    private void injectPresenter() {
        DaggerCallSendingComponent.builder()
                .appComponent(DistressSenderApp.getAppComponent())
                .callSendingModule(new CallSendingModule(this))
                .build()
                .inject(this);
    }

    /**
     * Handle click on distress call button.
     *
     * @param view
     */
    public void onSendDistressCallClick(View view) {
        // ask the presenter to send a distress call
        mPresenter.sendDistressCall();
    }
}

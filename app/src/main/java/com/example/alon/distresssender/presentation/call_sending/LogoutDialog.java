package com.example.alon.distresssender.presentation.call_sending;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Dialog fragment that handled user logout confirmation ui.
 */

public class LogoutDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Logout")
                .setMessage("Are you sure toy want to logout?")
                .setPositiveButton("OK", (dialog1, which) ->
                        ((DialogActionsListener)getActivity()).onLogoutConfirmed())
                .setNegativeButton("CANCEL",null)
                .create();
    }

    /**
     * {@link LogoutDialog} buttons clicks listener.
     */
    public interface DialogActionsListener {

        /**
         * Called when user clicks the OK
         * button in the {@link LogoutDialog}.
         */
        void onLogoutConfirmed();
    }
}

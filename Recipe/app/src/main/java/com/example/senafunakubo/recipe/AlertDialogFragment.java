package com.example.senafunakubo.recipe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by senafunakubo on 2017-09-15.
 */

public class AlertDialogFragment extends DialogFragment {

    public interface AlertDialogFragmentListener {
        void onFinishAlertDialogFragment(String result);
    }

    private AlertDialogFragmentListener listener;
    String ok = "ok";
//    String ng = "ng";

    // make sure the Activity implemented it
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (AlertDialogFragmentListener)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog ad = new AlertDialog.Builder(getActivity())
                .setTitle("Attention")
                .setMessage("Did you prepare ingredients before start cooking?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //show a toast message
                        Toast.makeText(getActivity().getApplicationContext(),"Yes",Toast.LENGTH_SHORT).show();
                        listener.onFinishAlertDialogFragment(ok);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //show a toast message
                        Toast.makeText(getActivity().getApplicationContext(),"CANCEL ",Toast.LENGTH_SHORT).show();
//                        listener.onFinishAlertDialogFragment(ng);
                    }
                })
                .create();

        return ad;
    }
}

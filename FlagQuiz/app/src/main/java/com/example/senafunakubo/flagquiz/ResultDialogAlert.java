package com.example.senafunakubo.flagquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by senafunakubo on 2017-08-03.
 */

public class ResultDialogAlert extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setTitle("Do you want to Restart the Quiz?")
                .setMessage(getString(R.string.results, MainActivityFragment.totalGuess,
                        (1000/(double)MainActivityFragment.totalGuess)))
                .setPositiveButton("RESET QUIZ",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Activity activity = getActivity(); //In a Fragment returns activity in which fragment is currently associated
                                Intent intent = new Intent(activity, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // completely cleared the entire stack and made the new activity the only one in the app
                                activity.finish();
                                startActivity(intent);
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                                Toast.makeText(getContext(),"you clicked cancel",Toast.LENGTH_SHORT).show();
                            }
                        }
                )
                .create();
    }
}

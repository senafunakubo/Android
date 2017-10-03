package com.example.senafunakubo.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by senafunakubo on 2017-07-26.
 */

public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog ad = new AlertDialog.Builder(getActivity())
                .setTitle("Alert Dialog")
                .setMessage("Choose option.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //show a toast message
                        Toast.makeText(getActivity().getApplicationContext(),"OK",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //show a toast message
                        Toast.makeText(getActivity().getApplicationContext(),"CANCEL ",Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        return ad;
    }
}

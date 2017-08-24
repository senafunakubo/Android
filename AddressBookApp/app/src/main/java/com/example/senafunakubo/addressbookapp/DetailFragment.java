package com.example.senafunakubo.addressbookapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.senafunakubo.addressbookapp.data.DatabaseDescription;

/**
 * Created by senafunakubo on 2017-08-23.
 */

public class DetailFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>{

    //create an ID for loader
    private static final int CONTACT_LOADER = 0;

    //uri of selected contact
    private Uri contactUri;
    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView emailTextView;
    private TextView streetTextView;
    private TextView cityTextView;
    private TextView stateTextView;
    private TextView zipTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //only this fragment is having option menu
        //in others it set to false
        setHasOptionsMenu(true);
        //create view from fragment detail.xml
        View view = inflater.inflate(
                R.layout.fragment_detail,
                container,
                false
        );

        //get the reference of textView
        nameTextView = (TextView)view.findViewById(R.id.nameTextView);
        phoneTextView = (TextView)view.findViewById(R.id.phoneTextView);
        emailTextView = (TextView)view.findViewById(R.id.emailTextView);
        streetTextView = (TextView)view.findViewById(R.id.streetTextView);
        cityTextView = (TextView)view.findViewById(R.id.cityTextView);
        stateTextView = (TextView)view.findViewById(R.id.stateTextView);
        zipTextView = (TextView)view.findViewById(R.id.zipTextView);

        //get the bundle value for selected contact Uri
        Bundle arg = getArguments();
        contactUri = arg.getParcelable(MainActivity.CONTACT_URI);

        //get the loader to load the contact
        getLoaderManager().initLoader(CONTACT_LOADER,
                null,this);
        return view;
    }

    //this method is called by loaderManager to create a loader
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader cursorLoader = new CursorLoader(
                getActivity(),
                contactUri, // Uri value which display a specific contact
                 null, // null projection returns all columns
                 null, //null selection return all rows
                 null, //null selection selection value
                 null  //sorting order
        );
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        //we should pass the data to UI
        //if you contact details in database exist
        if (cursor!=null && cursor.moveToFirst()){
            int nameIndex = cursor.getColumnIndex(DatabaseDescription.Contact.COLUMN_NAME);
            int phoneIndex = cursor.getColumnIndex(DatabaseDescription.Contact.COLUMN_PHONE);
            int emailIndex = cursor.getColumnIndex(DatabaseDescription.Contact.COLUMN_EMAIL);
            int streetIndex = cursor.getColumnIndex(DatabaseDescription.Contact.COLUMN_STREET);
            int cityIndex = cursor.getColumnIndex(DatabaseDescription.Contact.COLUMN_CITY);
            int stateIndex = cursor.getColumnIndex(DatabaseDescription.Contact.COLUMN_STATE);
            int zipIndex = cursor.getColumnIndex(DatabaseDescription.Contact.COLUMN_ZIP);

            //fill textView with retrieve data
            nameTextView.setText(cursor.getString(nameIndex));
            phoneTextView.setText(cursor.getString(phoneIndex));
            emailTextView.setText(cursor.getString(emailIndex));
            streetTextView.setText(cursor.getString(streetIndex));
            cityTextView.setText(cursor.getString(cityIndex));
            stateTextView.setText(cursor.getString(stateIndex));
            zipTextView.setText(cursor.getString(zipIndex));

        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

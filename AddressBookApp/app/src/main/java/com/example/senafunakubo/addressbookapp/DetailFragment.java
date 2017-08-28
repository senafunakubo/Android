package com.example.senafunakubo.addressbookapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.senafunakubo.addressbookapp.data.AddressBookContentProvider;
import com.example.senafunakubo.addressbookapp.data.DatabaseDescription;


/**
 * Created by senafunakubo on 2017-08-23.
 */

public class DetailFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>{

    FragmentManager fragmentManager = getFragmentManager();

    //callback methods implemented by MainActivity
    public interface DetailFragmentInterface{
        //2 methods for Edit & Delete
        //pass uri of the selected contact for editing
        void onEditContact(Uri uri);
        void onContactDeleted();
    }

    //object for interface
    public DetailFragmentInterface detailFragmentInterface;

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
        getLoaderManager().initLoader(CONTACT_LOADER, null, this);


        return view;
    }

    //initialize the interface when fragment is attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        detailFragmentInterface = (DetailFragmentInterface)context;
    }

    //Destroyed when fragment is detach
    @Override
    public void onDetach() {
        super.onDetach();
        detailFragmentInterface = null;
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_details_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case  R.id.action_edit :
                // it will take you to MainActivity
                detailFragmentInterface.onEditContact(contactUri);
                return  true;

            case R.id.action_delete:
                AlertDialog ad = new AlertDialog.Builder(getActivity())
                        .setTitle("ALERT")
                        .setMessage("Do you want to delete this data?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteContact();
                                Toast.makeText(getActivity().getApplicationContext(),"DELETE",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getActivity().getApplicationContext(),"CANCEL ",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                ad.show();

                return true;
        }
        return super.onOptionsItemSelected(item);
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

    public void deleteContact(){

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

}

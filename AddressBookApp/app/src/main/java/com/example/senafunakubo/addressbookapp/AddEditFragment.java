package com.example.senafunakubo.addressbookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.senafunakubo.addressbookapp.data.DatabaseDescription;

/**
 * Created by senafunakubo on 2017-08-22.
 */

   //use LoaderManager.LoaderCallback<> interface
   //for loading Async data from content provider
public class AddEditFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>
{
    //interface
    public interface AddEditFragmentInterface{
        void onAddEditComplete(Uri uri);
    }

    public AddEditFragmentInterface addEditFragmentInterface;

    //Every Loader needs a Unique ID
    //Integer constant for Loader
    private static final int CONTACT_LOADER = 0;
    //create a contact uri
    private Uri contactUri;
    //contactUri value will come from MainActivity



    //EditText for contact

    private TextInputLayout nameTextInput;
    private TextInputLayout phoneTextInput;
    private TextInputLayout emailTextInput;
    private TextInputLayout streetTextInput;
    private TextInputLayout cityTextInput;
    private TextInputLayout stateTextInput;
    private TextInputLayout zipTextInput;
    private FloatingActionButton saveContactFAB;

    //check whether insert or update
    private boolean addingNewContact = true;

    //create a View for Fragment



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //inflate the GUI and get the reference for EditText
        View view = inflater.inflate(
                R.layout.add_edit_fragment,
                container,
                false
        );
        nameTextInput = (TextInputLayout)view.findViewById(R.id.nameTextInputLayout);
        phoneTextInput = (TextInputLayout)view.findViewById(R.id.phoneTextInputLayout);
        emailTextInput =
                (TextInputLayout) view.findViewById(R.id.emailTextInputLayout);
        streetTextInput =
                (TextInputLayout) view.findViewById(R.id.streetTextInputLayout);
        cityTextInput =
                (TextInputLayout) view.findViewById(R.id.cityTextInputLayout);
        stateTextInput =
                (TextInputLayout) view.findViewById(R.id.stateTextInputLayout);
        zipTextInput =
                (TextInputLayout) view.findViewById(R.id.zipTextInputLayout);

        // set FloatingActionButton's event listener
        saveContactFAB = (FloatingActionButton) view.findViewById(
                R.id.saveFloatingActionButton);
        saveContactFAB.setOnClickListener(saveDataListner);

        //will have selected contact ID if editing
        //otherwise a null
        Bundle argument = getArguments();
        if (argument!= null){
            addingNewContact = false;
            contactUri = argument.getParcelable(MainActivity.CONTACT_URI);
        }
        //load the data fr selected contact from content
        //database reading operation
        if (contactUri != null){
            getLoaderManager().initLoader(
                    CONTACT_LOADER,
                    null,
                    this
            );
        }
        return view;
    }

    //create a click Listener for FAB
    private View.OnClickListener saveDataListner =
            new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    saveContact(); //this is method to save the data
                }
            };

    //saveContact() saves information into database
    // insert() : requires ContentVAlue
    private void saveContact()
    {
        ContentValues contentValues =
                new ContentValues();
        contentValues.put(DatabaseDescription.Contact.COLUMN_NAME,
                nameTextInput.getEditText().getText().toString() );
        contentValues.put(DatabaseDescription.Contact.COLUMN_PHONE,
                phoneTextInput.getEditText().getText().toString());
        contentValues.put(DatabaseDescription.Contact.COLUMN_EMAIL,
                emailTextInput.getEditText().getText().toString());
        contentValues.put(DatabaseDescription.Contact.COLUMN_STREET,
                streetTextInput.getEditText().getText().toString());
        contentValues.put(DatabaseDescription.Contact.COLUMN_CITY,
                cityTextInput.getEditText().getText().toString());
        contentValues.put(DatabaseDescription.Contact.COLUMN_STATE,
                stateTextInput.getEditText().getText().toString());
        contentValues.put(DatabaseDescription.Contact.COLUMN_ZIP,
                zipTextInput.getEditText().getText().toString());
        //Complete for all remaining column

        if (addingNewContact) {
            // you need a URI
            // this Uri is used yo call contentResolver
            // insert the data into addressBook Content
            Uri newContactUri = getActivity().
                    getContentResolver()
                    .insert(DatabaseDescription.Contact.CONTENT_URI
                            , contentValues);
            Toast.makeText(getActivity(), "Data inserted Successfully",
                    Toast.LENGTH_SHORT).show();
            //Change the Toast to SnackBar
            //SnackBar = notification feedback to the user
            // and you add actions to snackBar like undo, cancel, ok
            addEditFragmentInterface.onAddEditComplete(newContactUri);
        }
        else
        {
            //Use ContentResolvers' Update mehod
            //int will returns the int
            int updateRows = getActivity()
                    .getContentResolver()
                    .update(
                            contactUri,
                            contentValues,
                            null,
                            null
                    );

            //success
            if (updateRows > 0){
                Toast.makeText(
                        getContext(),R.string.contact_updated,
                        Toast.LENGTH_SHORT
                ).show();
                addEditFragmentInterface
                        .onAddEditComplete(contactUri);
                //onAddEdit() implemented in MainActivity
            }
            //failure
            else{
                Toast.makeText(
                        getContext(),
                        R.string.contact_not_updated,
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addEditFragmentInterface = (AddEditFragmentInterface)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        addEditFragmentInterface = null;
    }

    @Override
    //This one will create a LoderManager
    // Start Loading the data from Content Provider
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //Create a CursorLoader based on ID
        //getActivity() : this is a fragment attached to
        //the activity that's why we are using.
        CursorLoader c = new CursorLoader(
                getActivity(),
                contactUri, //Uri of the Contact to display
                null, //null means all columns
                null, //null returns all rows
                null, //No where clause
                null
        );
        return c;
    }

    @Override
    //Once the loading of data is finished than This method is called
    //from here you can give the data back to UI
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //Called when loading is completed.
        if(data!=null && data.moveToFirst())
        {
            //get the ColumnIndex for each data item
            int nameIndex = data.getColumnIndex(
                    DatabaseDescription.Contact.COLUMN_NAME);
            int phoneIndex = data.getColumnIndex(
                    DatabaseDescription.Contact.COLUMN_PHONE);
            int emailIndex = data.getColumnIndex(
                    DatabaseDescription.Contact.COLUMN_EMAIL);
            int streetIndex = data.getColumnIndex(
                    DatabaseDescription.Contact.COLUMN_STREET);
            int cityIndex = data.getColumnIndex(
                    DatabaseDescription.Contact.COLUMN_CITY);
            int stateIndex = data.getColumnIndex(
                    DatabaseDescription.Contact.COLUMN_STATE);
            int zipIndex = data.getColumnIndex(
                    DatabaseDescription.Contact.COLUMN_ZIP);

            // Find the remaining column iNdex
            //Fill my Edittext with retrieved data
            nameTextInput.getEditText().setText(
                    data.getString(nameIndex));
            phoneTextInput.getEditText().setText(
                    data.getString(phoneIndex));
            emailTextInput.getEditText().setText(
                    data.getString(emailIndex));
            streetTextInput.getEditText().setText(
                    data.getString(streetIndex));
            cityTextInput.getEditText().setText(
                    data.getString(cityIndex));
            stateTextInput.getEditText().setText(
                    data.getString(stateIndex));
            zipTextInput.getEditText().setText(
                    data.getString(zipIndex));

            // Fill the editText for remaining columns

        }

    }

    //It will reset the Loader
    //release the reference for Loader
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
}
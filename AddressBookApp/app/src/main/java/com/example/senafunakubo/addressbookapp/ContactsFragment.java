package com.example.senafunakubo.addressbookapp;


import android.content.Context;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senafunakubo.addressbookapp.data.DatabaseDescription;

/**
 * Created by senafunakubo on 2017-08-18.
 */

public class ContactsFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>{

//        ,ContactAdapter.ContactAdapterInterface {

    //from here you should go to MainActivity and load
    //detailFragment with selected ID
//    @Override
//    public void onClick(Uri uri) {
//        contactFragmentInterface.onContactSelected(uri);
//    }

    //interface : have only method no implementation
    //class implementing interface will have code
    public interface ContactFragmentInterface{
        void onAddContact();
        void onContactSelected(Uri uri);
    }

    private ContactAdapter contactAdapter;
    private int contact_loader =0;
    ContactFragmentInterface contactFragmentInterface; //declaration(not initialization)

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(
                R.layout.fragment_contacts,
                container,
                false
        );

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        //set layoutManager
        recyclerView.setLayoutManager(
                new LinearLayoutManager(
                        getActivity().getBaseContext()
                )
        );
        //set adapter
        contactAdapter =  new ContactAdapter(new ContactAdapter.ContactAdapterInterface() {
            @Override
            public void onClick(Uri uri) {
                contactFragmentInterface.onContactSelected(uri);
            }
        });

        recyclerView.setAdapter(contactAdapter);
        recyclerView.addItemDecoration(new ItemDivider(getContext()));

        //improving the performance
        recyclerView.setHasFixedSize(true);

        FloatingActionButton actionButton =
                (FloatingActionButton)view.findViewById(R.id.addButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactFragmentInterface.onAddContact(); //main activity
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        contactFragmentInterface = (ContactFragmentInterface)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        contactFragmentInterface = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(contact_loader,
                null,this);
    }

    //create a loader object and start loading the data into cursor
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        CursorLoader c = new CursorLoader(
                getActivity(),
                DatabaseDescription.Contact.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        return c;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        contactAdapter.notifyChange(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        contactAdapter.notifyChange(null);
    }


}

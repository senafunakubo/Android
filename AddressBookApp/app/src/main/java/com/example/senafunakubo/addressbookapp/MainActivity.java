package com.example.senafunakubo.addressbookapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.senafunakubo.addressbookapp.data.DatabaseDescription;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity
        implements ContactsFragment.ContactFragmentInterface,
        AddEditFragment.AddEditFragmentInterface,
        DetailFragment.DetailFragmentInterface{

    private ContactsFragment contactsFragment;
    public static final String CONTACT_URI = "contact_uri";
    public boolean phoneDevice = true;
    private Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //うまくいかん
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        contactsFragment = new ContactsFragment();

        //define a screen size
        int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        //if device is a tablet, set phoneDevice to false
        // if running on phone-sized device, allow only portrait orientation
        if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE)
            phoneDevice = false;

        if (phoneDevice){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragmentContainer,contactsFragment);
            fragmentTransaction.commit();
        }
        else { //Tablet & portrait_mode

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragmentContainer,contactsFragment);
                fragmentTransaction.commit();
            }
            else { //Tablet & landscape_mode

                FragmentTransaction fragmentTransactionForTab = getSupportFragmentManager().beginTransaction();
                fragmentTransactionForTab.add(R.id.left_fragment, contactsFragment);

                FloatingActionButton actionButton =
                        (FloatingActionButton)findViewById(R.id.addButton);
                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view,"Click the add button",Snackbar.LENGTH_SHORT).show();
//                        Toast.makeText(MainActivity.this,"Click the add button",Toast.LENGTH_SHORT).show();

                        if (findViewById(R.id.right_fragment) != null) {
                            AddEditFragment addEdit = new AddEditFragment();
                            FragmentTransaction fragmentTransactionForTab = getSupportFragmentManager().beginTransaction();
                            fragmentTransactionForTab.add(R.id.right_fragment, addEdit);
                            fragmentTransactionForTab.commit();
                        }
                    }
                });

//               displayAddEditFragment(R.id.fragmentContainer, null);
            }
        }
    }

    //display fragment for adding a new or editing an existing contact
    // ViewID is layoutID
    // contactUri is the path for contentProvider

    public void displayAddEditFragment(int ViewId, Uri contactUri){

        AddEditFragment addEditFragment = new AddEditFragment();

        if (contactUri!=null){
            Bundle argument = new Bundle();
            argument.putParcelable(CONTACT_URI,contactUri);
            addEditFragment.setArguments(argument);
        }

        //create the fragment using FragmentTransaction
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager()
                        .beginTransaction();
        fragmentTransaction.replace(ViewId, addEditFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onAddContact() {
        displayAddEditFragment(R.id.fragmentContainer,null);
    }

    @Override
    public void onContactSelected(Uri uri) {
        if (phoneDevice) {
            DetailFragment detailFragment = new DetailFragment();

            //use FragmentTransaction
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fragmentContainer, detailFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            //create a bundle object that will pass selected
            //row uri to detailFragment
            Bundle bundle = new Bundle();
            bundle.putParcelable(CONTACT_URI, uri);
            detailFragment.setArguments(bundle);
        }
        else{
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                DetailFragment detailFragment = new DetailFragment();

                //use FragmentTransaction
                FragmentTransaction fragmentTransaction =
                        getSupportFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.right_fragment, detailFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                //create a bundle object that will pass selected
                //row uri to detailFragment
                Bundle bundle = new Bundle();
                bundle.putParcelable(CONTACT_URI, uri);
                detailFragment.setArguments(bundle);
            }
        }
    }

    @Override
    public void onAddEditComplete(Uri uri) {
        getSupportFragmentManager()
                .popBackStack();
//        contactsFragment.updateContactList();
    }

    @Override
    public void onEditContact(Uri uri) {
        displayAddEditFragment(R.id.fragmentContainer,uri);
    }

    @Override
    public void onContactDeleted() {

    }
}
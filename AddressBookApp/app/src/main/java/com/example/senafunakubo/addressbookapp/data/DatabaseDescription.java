package com.example.senafunakubo.addressbookapp.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by senafunakubo on 2017-08-17.
 */

//This class is provider's name : Authority
//Uri to interact with ContentProvider
//Inner class Contract that description tablename, column names

public class DatabaseDescription {

    //1. ContentProvider's name :typically package Name
    public static final String AUTHORITY = "com.example.senafunakubo.addressbookapp.data";

    //2 base URI to interact with ContentProvider
    public  static final Uri BASE_CONTENT_URI =
            Uri.parse("content://" + AUTHORITY);

    //3. Inner class that defines content for contact table
    // _ID

    public static final class Contact implements BaseColumns
    {
        //give a table name
        public static final String TABLE_NAME = "contacts";
        // give all the columns a name
        public  static final String COLUMN_NAME = "name";
        public  static final String COLUMN_PHONE = "phone";
        public  static final String COLUMN_EMAIL = "email";
        public  static final String COLUMN_STREET = "street";
        public  static final String COLUMN_CITY = "city";
        public  static final String COLUMN_STATE  = "state";
        public  static final String COLUMN_ZIP ="zip";

        //Uri for the contact Table
        public  static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        //Create a uri for specific contact using id
        public  static final Uri buildContactUri(long id)
        {
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
    }


}
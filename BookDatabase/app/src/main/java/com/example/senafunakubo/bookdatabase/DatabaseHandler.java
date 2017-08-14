package com.example.senafunakubo.bookdatabase;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by senafunakubo on 2017-08-14.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    Context context;

    //TODO 1) Create a database
    //database name
    public static final String DATABASE_NAME = "Bookdb";

    //TODO 2) Current version of database
    private static int DATABASE_VERSION = 1;

    //TODO 3) Create constructor
    public DatabaseHandler(Context context){
        //3rd is always null
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //TODO 4) Create a Table name
    private static final String TABLE_NAME = "book";

    //TODO 5) ALL keys used in table with their data type & constraints
    private static final String KEY_ID = "bookid";
    private static final String KEY_NAME = "title";
    private static final String KEY_AUTHOR = "author";


    //TODO 6) Create a query statement for creating a table in database
    private static final String CREATE_TABLE_BOOKS =
            "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER " +
                    " PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, " + KEY_AUTHOR +
                    " TEXT " + ");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO 7) Create a a table Book
        db.execSQL(CREATE_TABLE_BOOKS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        // If you have exchanged or added or removes columns
        // in table than this method will be called
        db.execSQL("DROP TABLE IP EXIST " + CREATE_TABLE_BOOKS);
        this.onCreate(db);
    }

    public void addBook(Book book){

        // get the reference to writable DB
        SQLiteDatabase db1 = this.getWritableDatabase();

        // Create Content & value pair
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, book.getTitle());
        values.put(KEY_AUTHOR, book.getAuthor());

        //Insert the data into table
        db1.insert(TABLE_NAME, null, values);

        //close the database connection
        db1.close();

    }
}

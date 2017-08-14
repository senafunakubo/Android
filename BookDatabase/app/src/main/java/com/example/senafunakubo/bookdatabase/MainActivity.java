package com.example.senafunakubo.bookdatabase;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    DatabaseHandler dbHandler = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler.addBook(new Book("WINGS OF FIRE", "APJ KALAM"));
        dbHandler.addBook(new Book("THE WAVES", "VIRGINIA WOOLF"));
        Toast.makeText(this, "DATABASE CREATED  SUCCESSFULLY", Toast.LENGTH_SHORT).show();

        //data > data > com.example.sena~ > 該当のファイル
    }
}

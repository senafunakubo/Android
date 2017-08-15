package com.example.senafunakubo.bookdatabase;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //listView needs a separete layout to show data in row ... array adapter
    DatabaseHandler dbHandler = new DatabaseHandler(this);
    Button addBook;
    EditText bookTitle, bookAuthor;
    ListView lv_books;
    BookAdapter bookAdapter;
    List<Book> listBooks; //Book is the model class


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBook = (Button)findViewById(R.id.button_add);
        bookTitle = (EditText)findViewById(R.id.titleEdit);
        bookAuthor = (EditText)findViewById(R.id.authorEdit);
        lv_books = (ListView)findViewById(R.id.listView);
        addBook.setOnClickListener(this);

        listBooks = dbHandler.getAllBooks();
        List<Integer> listID = new ArrayList<Integer>();
        List<String> listTitle = new ArrayList<String>();
        List<String> listAuthor = new ArrayList<String>();

        for (int i=0; i<listBooks.size(); i++){

            listID.add(i,listBooks.get(i).getId());
            listTitle.add(i,listBooks.get(i).getTitle());
            listAuthor.add(i,listBooks.get(i).getAuthor());
        }

        bookAdapter = new BookAdapter(this,listID,listTitle,listAuthor);
        lv_books.setAdapter(bookAdapter);

//        dbHandler.addBook(new Book("WINGS OF FIRE", "APJ KALAM"));
//        dbHandler.addBook(new Book("THE WAVES", "VIRGINIA WOOLF"));
//        Toast.makeText(this, "DATABASE CREATED  SUCCESSFULLY", Toast.LENGTH_SHORT).show();

        //data > data > com.example.sena~ > 該当のファイル
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_add:
                add(v);
                break;
        }
    }

    public void add(View v)
    {
        String title = bookTitle.getText().toString();
        String author = bookAuthor.getText().toString();
        dbHandler.addBook(new Book(title,author));
        Toast.makeText(this, "BOOK ADDED SUCCESFULLY",
                Toast.LENGTH_LONG).show();

    }
}

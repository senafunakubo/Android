package com.example.senafunakubo.bookdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ListView.OnItemClickListener{
    //listView needs a separete layout to show data in row ... array adapter
//    Context context;
    DatabaseHandler dbHandler = new DatabaseHandler(this);
    String tag = "book";
    Button addBook,updateButton,deleteButton,sortByTitle,sortByAuthor;
    EditText bookTitle, bookAuthor;
    ListView lv_books;
    BookAdapter bookAdapter;
    List<Book> listBooks; //Book is the model class
    static Integer selectedID, selectedRow;
    // global variable that stores
    // selected book's id


    List<Integer> listID = new ArrayList<Integer>();
    final List<String> listTitle = new ArrayList<String>();
    final List<String> listAuthor = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBook = (Button)findViewById(R.id.button_add);
        updateButton = (Button)findViewById(R.id.update);
        deleteButton = (Button)findViewById(R.id.delete);
        sortByTitle = (Button)findViewById(R.id.sortByT);
        sortByAuthor = (Button)findViewById(R.id.sortByA);
        bookTitle = (EditText)findViewById(R.id.titleEdit);
        bookAuthor = (EditText)findViewById(R.id.authorEdit);
        lv_books = (ListView)findViewById(R.id.listView);

        //clickListener
        addBook.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        sortByTitle.setOnClickListener(this);
        sortByAuthor.setOnClickListener(this);
        lv_books.setOnItemClickListener(this);


        //todo 8) Read all books from database and add it into the list.
        listBooks = dbHandler.getAllBooks("");


        for (int i=0; i<listBooks.size(); i++){

            listID.add(i,listBooks.get(i).getId());
            listTitle.add(i,listBooks.get(i).getTitle());
            listAuthor.add(i,listBooks.get(i).getAuthor());
        }

        bookAdapter = new BookAdapter(this,listID,listTitle,listAuthor);
        lv_books.setAdapter(bookAdapter);


        if (bookTitle.isSelected() || bookAuthor.isSelected()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }

//        dbHandler.addBook(new Book("WINGS OF FIRE", "APJ KALAM"));
//        dbHandler.addBook(new Book("THE WAVES", "VIRGINIA WOOLF"));
//        Toast.makeText(this, "DATABASE CREATED  SUCCESSFULLY", Toast.LENGTH_SHORT).show();

        //How to check?
        //Tools > Android > Android Device Monitor > data > data > com.example.sena~ > 該当のファイル
    }


    public void getAllItem(String sort){

            listID.clear();
            listTitle.clear();
            listAuthor.clear();
            listBooks.clear();

        listBooks = dbHandler.getAllBooks(sort);

        for(int i = 0; i < listBooks.size(); i++){
            listID.add(i, listBooks.get(i).getId());
            listTitle.add(i, listBooks.get(i).getTitle());
            listAuthor.add(i, listBooks.get(i).getAuthor());
        }

            bookAdapter = new BookAdapter(this, listID, listTitle, listAuthor);
            lv_books.setAdapter(bookAdapter);
            bookAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_add:
                if (bookTitle.length()!=0 && bookAuthor.length()!=0) {
                    dbHandler.addBook(new Book(bookTitle.getText().toString(), bookAuthor.getText().toString()));
                    Toast.makeText(this, "BOOK ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    getAllItem("");
                }
                else{
                    Toast.makeText(this,"You must put something in the text field!",Toast.LENGTH_SHORT).show();
                }
                break;

            //create a book object with selected ID
            //Pass this book object to update method
            //to DatabaseHandler.java
            case R.id.update:
                //empty object of book
                Book book = new Book();
                Log.d("selectedID", Integer.toString(selectedID));
                book.setId(selectedID);
                book.setTitle(bookTitle.getText().toString());
                book.setAuthor(bookAuthor.getText().toString());
                int rowAffected = dbHandler.updateBook(book);

                listTitle.set(selectedRow, bookTitle.getText().toString());
                listAuthor.set(selectedRow, bookAuthor.getText().toString());

                //important
                bookAdapter.notifyDataSetChanged();
                Toast.makeText(this,"ROWS" + rowAffected + "ARE UPDATED",Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete:
                Book book1 = new Book();
                book1.setId(selectedID);
                book1.setTitle(bookTitle.getText().toString());
                book1.setAuthor(bookAuthor.getText().toString());
                int rowAffected1 = dbHandler.deleteBook(book1);
                listID.remove(selectedRow);
                listTitle.remove(selectedRow);
                listAuthor.remove(selectedRow);
                bookAdapter.notifyDataSetChanged();
                Toast.makeText(this,"ROWS" + rowAffected1 + "ARE DELETED",Toast.LENGTH_SHORT).show();
                break;

            case R.id.sortByT:
                getAllItem("title");
                Toast.makeText(this,"ROWS ARE SORTED BY TITLE", Toast.LENGTH_SHORT).show();
                break;

            case R.id.sortByA:
                getAllItem("author");
                Toast.makeText(this,"ROWS ARE SORTED BY AUTHOR", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedID = listBooks.get(position).getId();
        selectedRow = position;
        Book b = dbHandler.readBook(selectedID);
        bookTitle.setText(b.getTitle());
        bookAuthor.setText(b.getAuthor());
        Toast.makeText(this, "BOOK READ SUCCESSFULLY", Toast.LENGTH_SHORT).show();
    }

}

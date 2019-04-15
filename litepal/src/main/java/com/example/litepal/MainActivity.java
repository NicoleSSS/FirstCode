package com.example.litepal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Book book;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCreate = findViewById(R.id.create_database);
        btnCreate.setOnClickListener(this);
        Button btnAdd = findViewById(R.id.add_data);
        btnAdd.setOnClickListener(this);
        Button btnUpdate = findViewById(R.id.update_data);
        btnUpdate.setOnClickListener(this);
        Button btnQuery = findViewById(R.id.query_data);
        btnQuery.setOnClickListener(this);
        Button btnDelete = findViewById(R.id.delete_data);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_database:
                Connector.getDatabase();
                break;
            case R.id.add_data:
                book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(15.96);
                book.setPress("unknown");
                book.save();
                break;
            case R.id.update_data:
                book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?",
                        "The Lost Symbol","Dan Brown");
                break;
            case R.id.query_data:
                List<Book> books = LitePal.findAll(Book.class);
                for(Book book : books){
                    Log.d(TAG, "book name is " + book.getName());
                    Log.d(TAG, "book price is " + book.getPrice());
                }
                break;
            case R.id.delete_data:
                LitePal.deleteAll(Book.class, "price > ?" , "15");
                break;
            default:
                break;
        }
    }
}

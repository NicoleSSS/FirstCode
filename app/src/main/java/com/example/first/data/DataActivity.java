package com.example.first.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.first.R;

public class DataActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DataActivity";

    private EditText editText;

    private SharedPreferences.Editor editor;
    private SharedPreferences pref;

    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private ContentValues values;

    private String name = null;
    private String author = null;
    private int pages = 0;
    private double price = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        editText = findViewById(R.id.et_data);
        String inputText = FileUtils.load(this);
        if (!TextUtils.isEmpty(inputText)) {
            editText.setText(inputText);
            //将光标移动到文末
            editText.setSelection(inputText.length());
            Toast.makeText(this, "Restoring successed", Toast.LENGTH_SHORT).show();
        }
        Button button = findViewById(R.id.btn_save_pre);
        button.setOnClickListener(this);
        Button button1 = findViewById(R.id.btn_restore_pre);
        button1.setOnClickListener(this);

        dbHelper = new MyDatabaseHelper(this, "BookStore", null, 2);

        Button btnCreate = findViewById(R.id.btn_create_db);
        btnCreate.setOnClickListener(this);
        Button btnAdd = findViewById(R.id.btn_add_data);
        btnAdd.setOnClickListener(this);
        Button btnUpdate = findViewById(R.id.btn_update_data);
        btnUpdate.setOnClickListener(this);
        Button btnQuery = findViewById(R.id.btn_query_data);
        btnQuery.setOnClickListener(this);
        Button btnDelete = findViewById(R.id.btn_delete_data);
        btnDelete.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = editText.getText().toString();
        FileUtils.save(this, inputText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_pre:
                editor = getSharedPreferences("data",
                        MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 27);
                editor.putBoolean("married", false);
                editor.apply();
                break;
            case R.id.btn_restore_pre:
                pref = getSharedPreferences("data", MODE_PRIVATE);
                name = pref.getString("name", "");
                int age = pref.getInt("age", 0);
                boolean married = pref.getBoolean("married", false);
                Log.d(TAG, "name is " + name);
                Log.d(TAG, "age is " + age);
                Log.d(TAG, "married is " + married);
                break;
            case R.id.btn_create_db:
                dbHelper.getWritableDatabase();
                break;
            case R.id.btn_add_data:
                db = dbHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 19.69);
                db.insert("Book", null, values);
                values.clear();
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values);
                break;
            case R.id.btn_update_data:
                db = dbHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("price", "10.99");
                db.update("Book", values,
                        "name = ?", new String[]{"The Da Vinci Code"});
                break;
            case R.id.btn_query_data:
                db = dbHelper.getWritableDatabase();
                //查询book表中的所有数据
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        name = cursor.getString(cursor.getColumnIndex("name"));
                        author = cursor.getString(cursor.getColumnIndex("author"));
                        pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "book name is " + name);
                        Log.d(TAG, "book author is " + author);
                        Log.d(TAG, "book pages is " + pages);
                        Log.d(TAG, "book price is" + price);
                    } while (cursor.moveToNext());

                    //cursor必须关闭
                    cursor.close();
                }
                break;
            case R.id.btn_delete_data:
                db = dbHelper.getWritableDatabase();
                db.delete("Book", "pages > ?", new String[]{"500"});
                break;
            default:
                break;
        }
    }
}

package com.example.first.ui.listview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.first.R;
import com.example.first.ui.Fruit;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private String[] data = {"Apple", "Banana", "Orange", "Watermelon",
            "Apple", "Banana", "Orange", "Watermelon",
            "Apple", "Banana", "Orange", "Watermelon",
            "Apple", "Banana", "Orange", "Watermelon"};

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        initFruitAdapter();
    }


    //简单实现存储String的ListView
    private void initSimpleAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ListViewActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(arrayAdapter);
    }

    private void initFruitAdapter() {
        for (int i = 0; i < 4; i++) {
            Fruit app = new Fruit("Apple", R.mipmap.ic_launcher);
            fruitList.add(app);

            Fruit banana = new Fruit("Banana", R.mipmap.ic_launcher);
            fruitList.add(banana);

            Fruit orange = new Fruit("Orange", R.mipmap.ic_launcher);
            fruitList.add(orange);

            Fruit pear = new Fruit("Pear", R.mipmap.ic_launcher);
            fruitList.add(pear);
        }

        FruitAdapter adapter = new FruitAdapter(ListViewActivity.this, R.layout.item_fruit, fruitList);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(ListViewActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

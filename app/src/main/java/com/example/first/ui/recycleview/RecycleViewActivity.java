package com.example.first.ui.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.first.R;
import com.example.first.ui.Fruit;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();
    private List<String> wordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        initWordAdapter();
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
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void initWordAdapter() {
        wordList.add("lobby");
        wordList.add("arable");
        wordList.add("sprawl");
        wordList.add("parasite");
        wordList.add("acquaintance");
        wordList.add("bespectacled");
        wordList.add("septic");
        wordList.add("mind");
        wordList.add("interdependent");

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        AutoLayoutManager manager = new AutoLayoutManager();
        recyclerView.setLayoutManager(manager);
        WordAdapter adapter = new WordAdapter(wordList);
        recyclerView.setAdapter(adapter);
    }

    private static final int MAX = 9;


    private int setSpanSize(int position, List<String> list) {
        int count;
        if (list.get(position).length() > MAX) {
            count = 2;
        } else {
            count = 1;
        }

        return count;
    }
}

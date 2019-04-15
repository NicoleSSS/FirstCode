package com.example.first.material;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.first.R;
import com.example.first.ui.Fruit;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * @ClassName: FruitActivity
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/4/12 11:20
 * @Version: 1.0
 */
public class FruitActivity extends AppCompatActivity {

    public static final String FRUIT = "fruit";
    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";
    private Intent intent;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView fruitImg;
    private TextView fruitContentText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        intent = getIntent();
//        String fruitName = intent.getStringExtra(FRUIT_NAME);
//        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);

        String fruitName = ((Fruit)intent.getParcelableExtra(FRUIT)).getName();
        int fruitImageId = ((Fruit)intent.getParcelableExtra(FRUIT)).getImageId();
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        fruitImg = findViewById(R.id.img_coll_fruit);
        fruitContentText = findViewById(R.id.tv_fruit_content);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImg);
        String fruitContent = generateFruitContent(fruitName);
        fruitContentText.setText(fruitContent);

    }


    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

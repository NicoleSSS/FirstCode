package com.example.first.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.first.R;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }
}

package com.example.first.launchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.first.R;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "launch_ThirdActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.d(TAG, "Task id is" + getTaskId());
    }
}

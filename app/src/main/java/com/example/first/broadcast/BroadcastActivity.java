package com.example.first.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.first.R;

public class BroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        Button button = findViewById(R.id.btn_broadcast);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                IntentFilter intentFilter = new IntentFilter();
//                intentFilter.addAction("com.example.first.broadcast.MY_BROADCAST");
//                MyBroadcast receiver = new MyBroadcast();
//                registerReceiver(receiver, intentFilter);

                Intent intent =new Intent();
                intent. setComponent(new ComponentName( "com.example.first.broadcast" ,
                        "MyBroadcast") );
//                intent.setPackage(getPackageName());
                intent.setAction("com.example.first.broadcast.MY_BROADCAST");
                sendBroadcast( intent );
            }
        });
    }


}

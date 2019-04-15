package com.example.first.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.first.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart;
    private Button btnStop;
    private Button btnBind;
    private Button btnUnbind;
    private Button btnStartIntent;

    private Intent intent;

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        btnStart = findViewById(R.id.btn_start_service);
        btnStart.setOnClickListener(this);
        btnStop = findViewById(R.id.btn_stop_service);
        btnStop.setOnClickListener(this);
        btnBind = findViewById(R.id.btn_bind_service);
        btnBind.setOnClickListener(this);
        btnUnbind = findViewById(R.id.btn_unbind_service);
        btnUnbind.setOnClickListener(this);
        btnStartIntent = findViewById(R.id.btn_start_intent_service);
        btnStartIntent.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.btn_stop_service:
                intent = new Intent(this, MyService.class);
                stopService(intent);
                break;
            case R.id.btn_bind_service:
                intent = new Intent(this, MyService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                unbindService(connection);
                break;
            case R.id.btn_start_intent_service:
                Log.d("ServiceActivity", "Thread id is "+ Thread.currentThread().getId());
                intent = new Intent(this,MyIntentService.class);
                startService(intent);
                break;
            default:
                break;
        }
    }
}

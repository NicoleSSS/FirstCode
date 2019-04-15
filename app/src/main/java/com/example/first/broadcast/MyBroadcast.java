package com.example.first.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @ClassName: MyBroadcast
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/4/8 9:29
 * @Version: 1.0
 */
public class MyBroadcast extends BroadcastReceiver {

    private static final String TAG = "MyBroadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        Toast.makeText(context, "received in MyBroadcast", Toast.LENGTH_SHORT).show();
    }
}

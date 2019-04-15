package com.example.first.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.first.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Button button = findViewById(R.id.btn_send_notice);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, ResponseActivity.class);
                PendingIntent pi = PendingIntent.getActivity(NotificationActivity.this, 0, intent, 0);
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                NotificationCompat.Builder builder = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("通知渠道ID", "通知渠道名称",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    channel.enableLights(true); //设置开启指示灯，如果设备有的话
                    channel.setLightColor(Color.RED); //设置指示灯颜色
                    channel.setShowBadge(true); //设置是否显示角标
                    channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);//设置是否应在锁定屏幕上显示此频道的通知
                    channel.setDescription("通知渠道描述");//设置渠道描述
                    channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 600});//设置震动频率
                    channel.setBypassDnd(true);//设置是否绕过免打扰模式
                    manager.createNotificationChannel(channel);
                    builder = new NotificationCompat.Builder(NotificationActivity.this, "通知渠道ID");
                } else {
                    builder = new NotificationCompat.Builder(NotificationActivity.this);
                }

                Notification notification = builder
                        .setContentTitle("我是标题")//标题
                        .setContentText("this is content text")// 详细内容
                        .setWhen(System.currentTimeMillis())//设置时间
                        .setSmallIcon(R.mipmap.ic_launcher)//设置小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//设置大图标
                        .setContentIntent(pi)
                        .setAutoCancel(true)//点击后消失
                        .setPriority(NotificationCompat.PRIORITY_MAX)//设置该通知优先级
//                        .setDefaults(NotificationCompat.DEFAULT_ALL)//打开呼吸灯，声音，震动，触发系统默认行为
//                        .setTicker("New message")//第一次推送，角标旁边显示的内容
                        /**
                         * Notification.DEFAULT_VIBRATE    //添加默认震动提醒  需要VIBRATE permission
                         * Notification.DEFAULT_SOUND    //添加默认声音提醒
                         * Notification.DEFAULT_LIGHTS//添加默认三色灯提醒
                         * Notification.DEFAULT_ALL//添加默认以上3种全部提醒*/
//                        .setLights(Color.YELLOW, 300, 0)//单独设置呼吸灯，一般三种颜色：红，绿，蓝，经测试，小米支持黄色
//                        .setSound(url)//单独设置声音
//                        .setVibrate(new long[]{100, 250, 100, 250, 100, 250})//单独设置震动
//                        .setColor(getResources().getColor(R.color.colorAccent))//设置smallIcon的背景色
                        /**
                         * android5.0加入了一种新的模式Notification的显示等级，共有三种：
                         * VISIBILITY_PUBLIC只有在没有锁屏时会显示通知
                         * VISIBILITY_PRIVATE任何情况都会显示通知
                         * VISIBILITY_SECRET在安全锁和没有锁屏的情况下显示通知*/
//                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)//设置该通知优先级
//                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)//设置通知类别
//                        .setFullScreenIntent(pi, true)//将Notification变为悬挂式Notification
                        .build();
                manager.notify(1, notification);
            }
        });
    }
}

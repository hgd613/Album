package com.scott.demo.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.scott.demo.BaseActivity;
import com.scott.demo.R;

public class NotificationActivity extends BaseActivity implements View.OnClickListener{

    private NotificationCompat.Builder builder;
    private TaskStackBuilder stackBuilder;
    private NotificationManager notificationManager;

    private NotificationCompat.InboxStyle inboxStyle;
    private Notification notification;

    private Button bt_simple_noti, bt_big_view_noti, download_view_noti;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        stackBuilder = TaskStackBuilder.create(this);

        bt_simple_noti = (Button) findViewById(R.id.bt_simple_noti);
        bt_simple_noti.setOnClickListener(this);

        bt_big_view_noti = (Button) findViewById(R.id.bt_big_view_noti);
        bt_big_view_noti.setOnClickListener(this);

        download_view_noti = (Button) findViewById(R.id.download_view_noti);
        download_view_noti.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_simple_noti:
                simpleNoti();
                break;
            case R.id.bt_big_view_noti:
                bigViewNoti();
                break;
            case R.id.download_view_noti:
                download();
                break;
        }
    }

    private void simpleNoti() {

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);
        notification = builder.build();

        builder.setContentTitle("特大新闻1")
                .setContentText("特大新闻特大新闻，鼠王发飙咬死两只猫")
                .setSmallIcon(R.mipmap.money100)
                .setAutoCancel(true)
                .setTicker("特大新闻1")
                .setDefaults(Notification.DEFAULT_VIBRATE);

        Intent nextIntent = new Intent(this, ResultActivity.class);
        stackBuilder.addNextIntent(nextIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setProgress(100, 50, false);

        notificationManager.notify(100, builder.build());
    }

    private void bigViewNoti() {

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);
        notification = builder.build();

        inboxStyle = new NotificationCompat.InboxStyle();

        builder.setContentTitle("特大新闻2");
        builder.setContentText("特大新闻特大新闻，鼠王发飙咬死两只猫");
        builder.setSmallIcon(R.mipmap.money100);
        builder.setAutoCancel(true); // 点击

        Intent nextIntent = new Intent(this, ResultActivity.class);
        stackBuilder.addNextIntent(nextIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);

        inboxStyle.setBigContentTitle("大视图标题2");
        inboxStyle.addLine("第一行");
        inboxStyle.addLine("第二行");
        inboxStyle.addLine("第三行");

        builder.setStyle(inboxStyle);

        notificationManager.notify(101, builder.build());
    }

    private void download() {

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);
        notification = builder.build();

        builder.setContentTitle("软件更新")
                .setSmallIcon(R.mipmap.money100)
                .setTicker("下载...") ;

        Intent nextIntent = new Intent(this, ResultActivity.class);
        stackBuilder.addNextIntent(nextIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setProgress(100, 0, false);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                for (int i = 0; i<=100; i++) {
                        builder.setProgress(100, i, true);
                }
                super.handleMessage(msg);
            }
        };

        notificationManager.notify(103, builder.build());
        handler.sendEmptyMessage(0);
    }


}

package com.bignerdranch.android.lightnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class NotificationActivity extends AppCompatActivity {
    private NotificationManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        findViewById(R.id.normal_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalNotification();
            }
        });

        findViewById(R.id.folder_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custom();
            }
        });
    }

    private void custom() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.folder_view);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this, 0, intent, 0);
        Notification notification = new Notification.Builder(NotificationActivity.this)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.launcher)
                .setContentTitle("折叠式通知")
                .setContentText("default")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .build();
        notification.bigContentView = remoteViews;//修改下配置文件，启用的有bug
        mManager.notify(1, notification);
    }

    private void normalNotification() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this, 0, intent, 0);
        Notification notification = new Notification.Builder(NotificationActivity.this)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.launcher)
                .setAutoCancel(true)
                .setContentTitle("普通通知")
                .setContentText("default")
                .build();

        mManager.notify(0, notification);
    }

}

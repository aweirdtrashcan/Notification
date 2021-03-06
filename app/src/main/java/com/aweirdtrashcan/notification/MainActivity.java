package com.aweirdtrashcan.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    NotificationCompat.Builder builder;
    NotificationManager notificationManager;
    String notificationTitle;
    String notificationContent;
    Button btnNotif;
    private final static int notificationId = 200;
    private final static String CHANNEL_ID = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNotif = findViewById(R.id.btnNotif);

        notificationTitle = getString(R.string.notificationTitle);
        notificationContent = getString(R.string.textContent);

        /* new intent to Start activity from click */
        Intent intent = new Intent(this, ExampleActivity.class);
        /* for what i understood, this is creating a new task in activity so i doesn't come on top
        of the Activity Stack */
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        int flag;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            flag = PendingIntent.FLAG_IMMUTABLE;
        } else {
            flag = 0;
        }

        /* Pending Intent - Basically declaring the intent i want to be opened
        in the Notification */
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, flag);
        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.notify(notificationId, builder.build());
            }
        });

        /* Notification Builder. Here i defined title, icon, content and Pending Intent
        To start a new activity whenever the user clicks in the Notification */
        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_people)
                .setContentTitle(notificationTitle)
                .setContentText(notificationContent)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(notificationContent))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        createNotificationChannel();

    }
    // Creating notification channels for newer versions of Android || notificationManager
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = getString(R.string.channelName);
            String descriptionText = getString(R.string.descriptionText);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(descriptionText);

            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
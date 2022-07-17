package com.aweirdtrashcan.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        TextView notf = findViewById(R.id.tvNotificationActivity);
        notf.setText(getString(R.string.notif_activity));
    }
}
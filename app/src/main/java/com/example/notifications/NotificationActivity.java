package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    TextView tvNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        tvNotification = findViewById(R.id.tvNotification);

        if(getIntent().hasExtra(MainActivity.NOTIFICATION_STRING))
        {
            String notificationText = getIntent().getStringExtra(MainActivity.NOTIFICATION_STRING);
            tvNotification.setText(notificationText);

            int notificationId = getIntent().getIntExtra(MainActivity.NOTIFICATION_ID,0);

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            manager.cancel(notificationId);

        }

    }
}

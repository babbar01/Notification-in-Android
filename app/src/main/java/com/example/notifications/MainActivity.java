package com.example.notifications;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_INTENT = 0;
    public static final String NOTIFICATION_STRING = "notificationPUTEXTRA";
    public static final String NOTIFICATION_ID = "notificationIdIntent";
    public static final int NOTICATION_CHANL1_ID = 1;
    Button btnOne,btnTwo;
    EditText etTitle,etText;

    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);

        etTitle = findViewById(R.id.etTitle);
        etText = findViewById(R.id.etText);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // important point to remember : above line


        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onbtnOneClicked();
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onbtnTwoClicked();
            }
        });


    }


    private void onbtnOneClicked() {

        String title = etTitle.getText().toString().trim();
        String text = etText.getText().toString().trim();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,App.CHANNEL_ONE);



        Intent activityIntent = new Intent(this,MainActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(
                this,REQUEST_INTENT,activityIntent,0);



        Intent actionActivityIntent = new Intent(this,NotificationActivity.class);
        actionActivityIntent.putExtra(NOTIFICATION_STRING,text);
        actionActivityIntent.putExtra(NOTIFICATION_ID,NOTICATION_CHANL1_ID);

        PendingIntent actionIntent = PendingIntent.getActivity(
                this,REQUEST_INTENT,actionActivityIntent,PendingIntent.FLAG_UPDATE_CURRENT);



        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(text)
                .setContentIntent(contentIntent)
                .addAction(R.mipmap.ic_launcher,"Open Activity",actionIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);
        // you cannot use NotificationManager.IMPORTANCE_HIGH here because it is introduced
        // above android nougat(N)

        manager.notify(NOTICATION_CHANL1_ID,builder.build());

    }

    private void onbtnTwoClicked() {

        String title = etTitle.getText().toString().trim();
        String text = etText.getText().toString().trim();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,App.CHANNEL_TWO);

        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_LOW);

        manager.notify(2,builder.build());
        // providing same id to notification will update the notification otherwise it will create
        //  another notification

    }
}

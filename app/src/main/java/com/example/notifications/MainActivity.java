package com.example.notifications;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

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

        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        // you cannot use NotificationManager.IMPORTANCE_HIGH here because it is introduced
        // above android nougat(N)

        manager.notify(1,builder.build());

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

package com.example.b.activity;


import android.Manifest;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.b.R;
import com.example.b.activity.MService.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public Bundle b;
    private Intent intent;

    private boolean flag;
    private boolean flag2;
    private ImageView mImageView;
    @Override
    protected void onStart() {
        super.onStart();
        if (b != null && b.getString("FROM") != null) {
            if (b.getString("FROM").equals("OK")) {
                String imageURL = b.getString("IMAGE_LINK");

                DateFormat df = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
                Date today = Calendar.getInstance().getTime();
                String imageDate = df.format(today);

                intent = new Intent();
                intent.setAction("sendToDatabase");
                intent.putExtra("FOR", "INSERT");
                intent.putExtra("IMAGE_URL", imageURL);
                intent.putExtra("IMAGE_DATE", imageDate);
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

                flag2 = true;
                linkViewer(imageURL);

            } else if (b.getString("FROM").equals("HISTORY")) {
                final String imageURL = b.getString("IMAGE_LINK");
                int imageStatus = b.getInt("IMAGE_STATUS");
                int imageID = b.getInt("IMAGE_ID");

                if (imageStatus == 1) {
                    intent = new Intent();
                    intent.setAction("sendToDatabase");
                    intent.putExtra("FOR", "DELETE");
                    intent.putExtra("IMAGE_ID", imageID);
                    intent.putExtra("IMAGE_URL", imageURL);
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

                    flag = false;
                    flag2 = false;
                    linkViewer(imageURL);

                    new CountDownTimer(15000, 1000) {

                        public void onTick(long millisUntilFinished) {
                        }
                        public void onFinish() {
                          
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "WORK", Toast.LENGTH_SHORT);
                            toast.show();

                            sendBroadcast(intent);
                            showToast("Ссылка : " + imageURL + " была удалена");
                        }
                    }.start();
                } else {
                    intent = new Intent();
                    intent.setAction("sendToDatabase");
                    intent.putExtra("FOR", "UPDATE");
                    intent.putExtra("IMAGE_ID", imageID);
                    intent.putExtra("IMAGE_DATE", b.getString("IMAGE_DATE"));
                    intent.putExtra("IMAGE_URL", b.getString("IMAGE_URL"));
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

                    flag = true;
                    flag2 = false;

                }
            }
        } else {
            closeApp();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = getIntent().getExtras();
        mImageView = findViewById(R.id.imageView);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
    }

    private void showToast(String str) {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
    }

    private void closeApp() {
        showToast("Приложение В не является самостоятельным приложением и будет закрыто через 10 секунд");
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                finish();
            }
        }.start();
    }

    public void linkViewer(String imageURL){
        Glide
                .with(this)
                .load(imageURL)
                .into(mImageView);
        startService(new Intent(this, MyService.class).putExtra("url",imageURL ) );

}

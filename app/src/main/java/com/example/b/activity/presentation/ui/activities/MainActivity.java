package com.example.b.activity.presentation.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.b.R;
import com.example.b.activity.presentation.ui.MyService;

public class MainActivity extends AppCompatActivity {
    public Bundle b;
    private Intent intent;
    private boolean flag;
    private boolean flag2;
    private ImageView mImageView;
    @Override
    protected void onStart() {
        super.onStart();
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
    }
    public void  saveOn(String imageURL) {
        startService(new Intent(this, MyService.class).putExtra("url",imageURL ) );}

}

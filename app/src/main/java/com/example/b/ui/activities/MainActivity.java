package com.example.b.ui.activities;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.b.R;
import com.example.b.model.service.ImageDownloadService;
import com.example.b.ui.fragments.MainFragment;

import static com.example.b.utils.Constants.DEFAULT;
import static com.example.b.utils.Constants.DEFAULT_ID;
import static com.example.b.utils.Constants.IMAGE_ID;
import static com.example.b.utils.Constants.IMAGE_STATUS;
import static com.example.b.utils.Constants.IMAGE_URL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout,
                            MainFragment.newInstance(getIntent().getStringExtra(IMAGE_URL),
                                    getIntent().getIntExtra(IMAGE_STATUS, DEFAULT),
                                    getIntent().getLongExtra(IMAGE_ID, DEFAULT_ID))).commit();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startService(new Intent(getApplicationContext(), ImageDownloadService.class).putExtra(IMAGE_URL, getIntent().getStringExtra(IMAGE_URL)));
        } else {
            Toast.makeText(getApplicationContext(), "Разрешите приложению доступ к файлам для сохранения изображения", Toast.LENGTH_LONG).show();
        }
    }
}


package com.example.b.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.b.R;
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

        // Alexander Rain: какого черта так сложно написано во фрагменте
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout,
                            MainFragment.newInstance(getIntent().getStringExtra(IMAGE_URL),
                                    getIntent().getIntExtra(IMAGE_STATUS, DEFAULT),
                                    getIntent().getLongExtra(IMAGE_ID, DEFAULT_ID))).commit();
        }
    }
}


package com.example.b.activity.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.b.activity.presentation.ui.activities.MainActivity;


public class BaseFragment extends Fragment implements BaseView {
    private ImageView mImageView;
    public Bundle b;
    private Intent intent;
    private boolean flag;
    private boolean flag2;
    @Override
    public void onStart() {
        super.onStart();
        MainActivity mainActivity = new MainActivity();
        mainActivity.check();
    }

   public void closeAPP(){
       MainActivity mainActivity = new MainActivity();
       mainActivity.closeApp();
   }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity mainActivity = new MainActivity();
        mainActivity.create();

    }
    public void linkViewerFragment(String imageURL){
        MainActivity mainActivity = new MainActivity();
        mainActivity.linkViewer(imageURL);
    }
    public void  saveOnSD(String imageURL) {
        MainActivity mainActivity = new MainActivity();
        mainActivity.saveOn(imageURL);
    }
    }


package com.example.b.ui.fragments;

import android.graphics.Bitmap;

import com.bumptech.glide.request.RequestListener;

public interface MainFragmentView {

    void showImage(String imageUrl, RequestListener<Bitmap> requestListener);
    void saveOnPath(String imageUrl);
    void closeApp();

}

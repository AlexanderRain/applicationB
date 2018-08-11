package com.example.b.activity.model.interactors;

import android.content.Context;

import com.example.b.activity.model.repository.MainRepository;

public class MainInteractor {

    MainRepository repository;

    public MainInteractor(Context context) {
        repository = new MainRepository(context);
    }

    public void insertImage(String imageUrl, int imageStatus) {
        repository.insertImage(imageUrl, imageStatus);
    }

    public void updateImage(String imageUrl, int imageStatus, String imageDate) {
        repository.updateImage(imageUrl, imageStatus, imageDate);
    }

    public void deleteImage(String imageUrl, int imageStatus, String imageDate) {
        repository.deleteImage(imageUrl, imageStatus, imageDate);
    }

}
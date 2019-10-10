package com.example.b.model.interactors;

import android.content.Context;
import android.util.Log;

import com.example.b.model.repository.MainRepository;

public class MainInteractor {

    private MainRepository repository;

    public MainInteractor(Context context) {
        repository = new MainRepository(context);
    }

    public void insertImage(String imageUrl, int imageStatus) {
        repository.insertImage(imageUrl, imageStatus);
    }

    public void updateImage(String imageUrl, int imageStatus, long imageId) {
        repository.updateImage(imageUrl, imageStatus, imageId);
    }

    public void deleteImage(String imageUrl, int imageStatus, long imageId) {
        repository.deleteImage(imageUrl, imageStatus, imageId);
    }

}
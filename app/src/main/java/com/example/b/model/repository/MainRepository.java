package com.example.b.model.repository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.b.utils.Constants;

import java.util.Date;

public class MainRepository {

    private Context context;

    public MainRepository(Context context) {
        this.context = context;
    }

    public void insertImage(String imageUrl, int imageStatus) {
        Intent intent = new Intent(Constants.RECEIVE);

        intent.putExtra(Constants.IMAGE_ACTION, Constants.INSERT);
        intent.putExtra(Constants.IMAGE_URL, imageUrl);
        intent.putExtra(Constants.IMAGE_STATUS, imageStatus);
        intent.putExtra(Constants.IMAGE_DATE, getImageDate());

        context.sendBroadcast(intent);
        Log.e("Log", " SEND INSERT");
    }

    public void updateImage(String imageUrl, int imageStatus, long imageId) {
        Intent intent = new Intent(Constants.RECEIVE);

        intent.putExtra(Constants.IMAGE_ACTION, Constants.UPDATE);
        intent.putExtra(Constants.IMAGE_URL, imageUrl);
        intent.putExtra(Constants.IMAGE_STATUS, imageStatus);
        intent.putExtra(Constants.IMAGE_ID, imageId);
        intent.putExtra(Constants.IMAGE_DATE, getImageDate());

        context.sendBroadcast(intent);
        Log.e("Log", " SEND UPDATE");
    }

    public void deleteImage(String imageUrl, int imageStatus, long imageId) {
        Intent intent = new Intent(Constants.RECEIVE);

        intent.putExtra(Constants.IMAGE_ACTION, Constants.DELETE);
        intent.putExtra(Constants.IMAGE_URL, imageUrl);
        intent.putExtra(Constants.IMAGE_STATUS, imageStatus);
        intent.putExtra(Constants.IMAGE_ID, imageId);

        context.sendBroadcast(intent);
        Log.e("Log", " SEND DELETE");
    }

    private String getImageDate() {
        return new Date(System.currentTimeMillis()).toString();
    }
}

package com.example.b.model.repository;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.example.b.utils.Constants;

import java.util.Date;

import static com.example.b.utils.Constants.HANDLER_PACKAGE;
import static com.example.b.utils.Constants.RECEIVER;

public class MainRepository {

    private Context context;
    private Intent intent;

    public MainRepository(Context context) {
        this.context = context;
        this.intent = new Intent(RECEIVER).setComponent(
                new ComponentName(HANDLER_PACKAGE, RECEIVER));
    }

    public void insertImage(String imageUrl, int imageStatus) {
        intent.putExtra(Constants.IMAGE_ACTION, Constants.INSERT);
        intent.putExtra(Constants.IMAGE_URL, imageUrl);
        intent.putExtra(Constants.IMAGE_STATUS, imageStatus);
        intent.putExtra(Constants.IMAGE_DATE, getImageDate());

        context.sendBroadcast(intent);
    }

    public void updateImage(String imageUrl, int imageStatus, long imageId) {
        Intent intent = new Intent(RECEIVER);
        intent.putExtra(Constants.IMAGE_ACTION, Constants.UPDATE);
        intent.putExtra(Constants.IMAGE_URL, imageUrl);
        intent.putExtra(Constants.IMAGE_STATUS, imageStatus);
        intent.putExtra(Constants.IMAGE_ID, imageId);
        intent.putExtra(Constants.IMAGE_DATE, getImageDate());
        intent.setComponent(
                new ComponentName(HANDLER_PACKAGE, RECEIVER));

        context.sendBroadcast(intent);
    }

    public void deleteImage(String imageUrl, int imageStatus, long imageId) {
        Intent intent = new Intent(RECEIVER);
        intent.putExtra(Constants.IMAGE_ACTION, Constants.DELETE);
        intent.putExtra(Constants.IMAGE_URL, imageUrl);
        intent.putExtra(Constants.IMAGE_STATUS, imageStatus);
        intent.putExtra(Constants.IMAGE_ID, imageId);
        intent.setComponent(
                new ComponentName(HANDLER_PACKAGE, RECEIVER));

        context.sendBroadcast(intent);
    }

    private String getImageDate() {
        return new Date(System.currentTimeMillis()).toString();
    }
}

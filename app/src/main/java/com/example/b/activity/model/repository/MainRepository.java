package com.example.b.activity.model.repository;

import android.content.Context;
import android.content.Intent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.b.activity.utils.Constants.DELETE;
import static com.example.b.activity.utils.Constants.IMAGE_ACTION;
import static com.example.b.activity.utils.Constants.IMAGE_DATE;
import static com.example.b.activity.utils.Constants.IMAGE_STATUS;
import static com.example.b.activity.utils.Constants.IMAGE_URL;
import static com.example.b.activity.utils.Constants.INSERT;
import static com.example.b.activity.utils.Constants.RECIVE;
import static com.example.b.activity.utils.Constants.UPDATE;

public class MainRepository {
    //intents
    Context context;

    public MainRepository(Context context) {
        this.context = context;
    }

    public void insertImage(String imageUrl, int imageStatus) {
        Intent intent = new Intent(RECIVE);

        intent.putExtra(IMAGE_ACTION, INSERT);
        intent.putExtra(IMAGE_URL, imageUrl);
        intent.putExtra(IMAGE_STATUS, imageStatus);
        intent.putExtra(IMAGE_DATE, getImageDate());

        context.sendBroadcast(intent);
    }

    public void updateImage(String imageUrl, int imageStatus) {
        Intent intent = new Intent(RECIVE);

        intent.putExtra(IMAGE_ACTION, UPDATE);
        intent.putExtra(IMAGE_URL, imageUrl);
        intent.putExtra(IMAGE_STATUS, imageStatus);
        intent.putExtra(IMAGE_DATE, getImageDate());

        context.sendBroadcast(intent);
    }

    public void deleteImage(String imageUrl, int imageStatus) {
        Intent intent = new Intent(RECIVE);

        intent.putExtra(IMAGE_ACTION, DELETE);
        intent.putExtra(IMAGE_URL, imageUrl);

        context.sendBroadcast(intent);
    }

    public String getImageDate() {
        DateFormat df = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String imageDate = df.format(today);
        return imageDate;
    }
}

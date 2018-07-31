package com.example.b.activity;

import com.example.b.activity.MainActivity.*;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MService extends Service {
    public void onCreate() {
        super.onCreate();
    }
    MainActivity mainActivity = new MainActivity();
    String url1 = mainActivity.b.getString("IMAGE_LINK");
    public int onStartCommand(Intent intent, int flags, int startId) {
        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {

        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
    void someTask() {
        new Thread(new Runnable() {
            public void run() {
                saveOnSDCard(url1);
                stopSelf();
            }
        }).start();
    }
    public void saveOnSDCard(String url) {
        File dir = new File(Environment.getExternalStorageDirectory() + "/BIGDIG/test/B");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String[] array = url.split("/");
        url = array[array.length - 1];
        url = url.substring(0, url.indexOf("."));

        File file = new File(dir, url + ".png");

        OutputStream os;
        try {
            os = new FileOutputStream(file);
            os.flush();
            os.close();
        } catch (IOException ioe) {
            Toast.makeText(this, ("Ошибка"+ioe.toString()), Toast.LENGTH_SHORT).show();
        }
        stopSelf();
    }
}

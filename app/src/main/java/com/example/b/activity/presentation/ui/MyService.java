package com.example.b.activity.presentation.ui;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyService extends IntentService {


    public MyService() {
        super("myname");
    }

    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String url1 = intent.getStringExtra("url");
        saveOnSDCard(url1);
      //  file_download(url1);//

    }

    public void onDestroy() {
        super.onDestroy();
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
        } catch (IOException ioe) {    Toast toast = Toast.makeText(getApplicationContext(),
                ("Ошибка"+ioe.toString()), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
//    public void file_download(String uRl) {
//        File direct = new File(Environment.getExternalStorageDirectory()
//                + "/BImage");
//
//        if (!direct.exists()) {
//            direct.mkdirs();
//        }
//
//        DownloadManager mgr = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
//
//        Uri downloadUri = Uri.parse(uRl);
//        DownloadManager.Request request = new DownloadManager.Request(
//                downloadUri);
//
//        request.setAllowedNetworkTypes(
//                DownloadManager.Request.NETWORK_WIFI
//                        | DownloadManager.Request.NETWORK_MOBILE)
//                .setAllowedOverRoaming(false).setTitle("Назва")
//                .setDescription("Опис")
//                .setDestinationInExternalFilesDir(this,"/BImage", "test.jpg");
//
//        mgr.enqueue(request);
//
//    }
}
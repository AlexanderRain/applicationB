package com.example.b.activity.model.service;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import static com.example.b.activity.utils.Constants.IMAGE_URL;
import static com.example.b.activity.utils.Constants.JPG;
import static com.example.b.activity.utils.Constants.PATH;
import static com.example.b.activity.utils.Constants.SERVICE_NAME;

public class ImageDownloadService extends IntentService {

    public ImageDownloadService() {
        super(SERVICE_NAME);
    }

    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String imageUrl = intent.getStringExtra(IMAGE_URL);
        file_download(imageUrl);
    }

    public void file_download(String imageUrl) {
        File file = new File(PATH);
        if (file.exists() && file.isDirectory()) {
            DownloadManager downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(imageUrl);

            DownloadManager.Request request = new DownloadManager.Request(downloadUri);

            request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setDestinationInExternalPublicDir(PATH,imageUrl +JPG);

            downloadManager.enqueue(request);
        } else {
            try {
                file.createNewFile();
            } catch (IOException ioe) {
                Log.e("Log",  "Exception " + ioe);
            }
            DownloadManager downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(imageUrl);

            DownloadManager.Request request = new DownloadManager.Request(downloadUri);

            request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setDestinationInExternalPublicDir(PATH, imageUrl + JPG);

            downloadManager.enqueue(request);
        }
    }

    public void onDestroy () {
        super.onDestroy();
    }

}

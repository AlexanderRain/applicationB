package com.example.b.activity.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.b.R;
import com.example.b.activity.ui.fragments.MainFragment;

import static com.example.b.activity.utils.Constants.DEFAULT;
import static com.example.b.activity.utils.Constants.IMAGE_DATE;
import static com.example.b.activity.utils.Constants.IMAGE_STATUS;
import static com.example.b.activity.utils.Constants.IMAGE_URL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Alexander Rain: какого черта так сложно написано во фрагменте
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout,
                            MainFragment.newInstance(getIntent().getStringExtra(IMAGE_URL),
                                    getIntent().getIntExtra(IMAGE_STATUS, DEFAULT),
                                    getIntent().getStringExtra(IMAGE_DATE))).commit();
        }
    }
}

    /*public Bundle b;
    private ImageView mImageView;
    private Intent intent;
    private boolean flag;
    private boolean flag2;
    @Override
    protected void onStart() {
        super.onStart();
        check();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create();
    }
    public void create(){
        b = getIntent().getExtras();

        mImageView = findViewById(R.id.imageView);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
    }

    public void closeApp() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Приложение В не является самостоятельным приложением и будет закрыто через 10 секунд", Toast.LENGTH_SHORT);
        toast.show();
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                finish();
            }
        }.start();
    }


    public void linkViewer(String imageURL){
        Glide
                .with(this)
                .load(imageURL)
                .into(mImageView);
    }
    public void  saveOn(String imageURL) {
        startService(new Intent(this, MyService.class).putExtra("url",imageURL ) );}


    public void check(){ if (b != null && b.getString("FROM") != null) {
        if (b.getString("FROM").equals("OK")) {
            String imageURL = b.getString("IMAGE_LINK");

            DateFormat df = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
            Date today = Calendar.getInstance().getTime();
            String imageDate = df.format(today);

            intent = new Intent();
            intent.setAction("sendToDatabase");
            intent.putExtra("FOR", "INSERT");
            intent.putExtra("IMAGE_URL", imageURL);
            intent.putExtra("IMAGE_DATE", imageDate);
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

            flag2 = true;
            linkViewer(imageURL);
            saveOn(imageURL);
        } else if (b.getString("FROM").equals("HISTORY")) {
            final String imageURL = b.getString("IMAGE_LINK");
            int imageStatus = b.getInt("IMAGE_STATUS");
            int imageID = b.getInt("IMAGE_ID");

            if (imageStatus == 1) {
                intent = new Intent();
                intent.setAction("sendToDatabase");
                intent.putExtra("FOR", "DELETE");
                intent.putExtra("IMAGE_ID", imageID);
                intent.putExtra("IMAGE_URL", imageURL);
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

                flag = false;
                flag2 = false;
                linkViewer(imageURL);
                saveOn(imageURL);

                new CountDownTimer(15000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        sendBroadcast(intent);
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Ссылка : " + imageURL + " была удалена", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }.start();
            } else {
                intent = new Intent();
                intent.setAction("sendToDatabase");
                intent.putExtra("FOR", "UPDATE");
                intent.putExtra("IMAGE_ID", imageID);
                intent.putExtra("IMAGE_DATE", b.getString("IMAGE_DATE"));
                intent.putExtra("IMAGE_URL", b.getString("IMAGE_URL"));
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

                flag = true;
                flag2 = false;
                linkViewer(imageURL);
                saveOn(imageURL);
            }
        }
    } else {
        closeApp();
    }}*/


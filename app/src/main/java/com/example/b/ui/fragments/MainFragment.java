package com.example.b.ui.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.example.b.R;
import com.example.b.model.service.ImageDownloadService;
import com.example.b.presenters.MainPresenter;
import com.example.b.utils.Constants;

import java.util.Objects;

import static com.example.b.utils.Constants.WRITE_EXTERNAL_PERMISSION;

public class MainFragment extends Fragment implements MainFragmentView {

    private View view;
    private ImageView imageView;
    private MainPresenter presenter;
    private Context context;
    private String currentImageUrl;

    // Что, что, что за метод, что тут происходит?
    // https://tttzof351.blogspot.com/2014/06/android.html
    public static MainFragment newInstance(String imageUrl, int imageStatus, long imageId) {
        MainFragment mainFragment = new MainFragment();
        Bundle extras = new Bundle();

        extras.putString(Constants.IMAGE_URL, imageUrl);
        extras.putInt(Constants.IMAGE_STATUS, imageStatus);
        extras.putLong(Constants.IMAGE_ID, imageId);
        mainFragment.setArguments(extras);

        return mainFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        context = getActivity();

        if(presenter == null){
            presenter = new MainPresenter(this, context);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imageView = view.findViewById(R.id.image);

        presenter.receiveExtras(getArguments().getString(Constants.IMAGE_URL), getArguments().getInt(Constants.IMAGE_STATUS), getArguments().getLong(Constants.IMAGE_ID));
    }

    @Override
    public void showImage(String imageUrl, RequestListener<Bitmap> requestListener) {
        // Alexander Rain:
        // https://bumptech.github.io/glide/doc/options.html#requestbuilder
        RequestBuilder<Bitmap> requestBuilder = Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .apply(new RequestOptions()
                        .error(R.drawable.load_failed));

        // Alexander Rain:
        // listener() sets a RequestBuilder listener to monitor the resource load
        if(requestBuilder != null) {
            requestBuilder.listener(requestListener).into(imageView);

        } else {
            // Alexander Rain: requestBuilder == null, when link status - INSERTED
            requestBuilder.into(imageView);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[WRITE_EXTERNAL_PERMISSION] == PackageManager.PERMISSION_GRANTED) {
            context.startService(new Intent(context, ImageDownloadService.class).putExtra(Constants.IMAGE_URL, currentImageUrl));
        } else {
            Toast.makeText(context, "Разрешите приложению доступ к файлам для сохранения картинок", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void saveOnPath(String imageUrl) {
        currentImageUrl = imageUrl;
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_PERMISSION);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            context.startService(new Intent(context, ImageDownloadService.class).putExtra(Constants.IMAGE_URL, imageUrl));
        }
    }

    @Override
    public void closeApp() {
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "Приложение В не является самостоятельным приложением и будет закрыто через 10 секунд", Toast.LENGTH_SHORT);
        toast.show();
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getActivity()!= null) {
                    getActivity().finish();
                    System.exit(0);
                }
            }
        },10000);
    }
}
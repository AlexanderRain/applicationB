package com.example.b.activity.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.b.activity.model.service.ImageDownloadService;
import com.example.b.activity.presenters.MainPresenter;

import static com.example.b.activity.utils.Constants.IMAGE_ID;
import static com.example.b.activity.utils.Constants.IMAGE_STATUS;
import static com.example.b.activity.utils.Constants.IMAGE_URL;
import static com.example.b.activity.utils.Constants.INSERTED;

public class MainFragment extends Fragment implements MainFragmentView {

    private View view;
    private ImageView imageView;
    private MainPresenter presenter;
    private Context context;

    // Что, что, что за метод, что тут происходит?
    // https://tttzof351.blogspot.com/2014/06/android.html
    public static MainFragment newInstance(String imageUrl, int imageStatus, long imageId) {
        MainFragment mainFragment = new MainFragment();
        Bundle extras = new Bundle();

        extras.putString(IMAGE_URL, imageUrl);
        extras.putInt(IMAGE_STATUS, imageStatus);
        extras.putLong(IMAGE_ID, imageId);
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

        presenter.receiveExtras(getArguments().getString(IMAGE_URL), getArguments().getInt(IMAGE_STATUS), getArguments().getLong(IMAGE_ID));
    }

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

    public void saveOnPath(String imageUrl) {
        context.startService(new Intent(context, ImageDownloadService.class).putExtra(IMAGE_URL, imageUrl));
    }

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
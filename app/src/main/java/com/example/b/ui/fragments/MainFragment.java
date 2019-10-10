package com.example.b.ui.fragments;

import android.Manifest;
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

import static com.example.b.utils.Constants.IMAGE_ID;
import static com.example.b.utils.Constants.IMAGE_STATUS;
import static com.example.b.utils.Constants.IMAGE_URL;
import static com.example.b.utils.Constants.WRITE_EXTERNAL_PERMISSION;

public class MainFragment extends Fragment implements MainFragmentView {

    private ImageView imageView;
    private MainPresenter presenter;

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

        if(presenter == null){
            presenter = new MainPresenter(this, getActivity());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        imageView = view.findViewById(R.id.image);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.receiveExtras(getArguments().getString(IMAGE_URL), getArguments().getInt(IMAGE_STATUS), getArguments().getLong(IMAGE_ID));
    }

    @Override
    public void showImage(String imageUrl, RequestListener<Bitmap> requestListener) {
        RequestBuilder<Bitmap> requestBuilder = Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .apply(new RequestOptions()
                        .error(R.drawable.load_failed));
            requestBuilder.listener(requestListener).into(imageView);

    }

    @Override
    public void saveOnPath(String imageUrl) {
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            getActivity().startService(new Intent(getActivity(), ImageDownloadService.class).putExtra(IMAGE_URL, imageUrl));
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_PERMISSION);
        }
    }

    @Override
    public void closeApp() {
        Toast.makeText(getActivity().getApplicationContext(),
                "Image-Viewer is not standalone it will clone in 10 seconds", Toast.LENGTH_SHORT)
                .show();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.imageView = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter = null;
    }
}
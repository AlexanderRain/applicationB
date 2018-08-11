package com.example.b.activity.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.b.activity.model.interactors.MainInteractor;
import com.example.b.activity.ui.fragments.MainFragmentView;

import static com.example.b.activity.utils.Constants.DEFAULT;
import static com.example.b.activity.utils.Constants.ERROR;
import static com.example.b.activity.utils.Constants.INSERTED;
import static com.example.b.activity.utils.Constants.UNDEFINED;

public class MainPresenter {

    private MainInteractor interactor;
    private MainFragmentView view;
    private Context context;

    public MainPresenter(MainFragmentView view, Context context) {
        interactor = new MainInteractor(context);
        this.view = view;
        this.context = context;
    }

    public void receiveExtras(String imageUrl, int imageStatus) {

        switch (imageStatus) {
            case DEFAULT:
                // Alexander Rain: imageUrl == nul, when app start from launcher
                if(imageUrl == null) {
                    view.closeApp();
                    break;
                }
                // Alexander Rain: this case for links, comes from test fragment
                view.showImage(imageUrl, getInsertRequestListener(imageUrl));
                break;

            case INSERTED:
                // Alexander Rain: this case for successful loaded links
                break;

            default:
                // Alexander Rain: this case for UNDEFINED and ERROR links
                interactor.updateImage(imageUrl, imageStatus);
        }
    }

    // Alexander Rain:
    // https://bumptech.github.io/glide/javadocs/400/com/bumptech/glide/request/RequestListener.html
    public RequestListener<Bitmap> getInsertRequestListener(final String imageUrl) {
        return new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                interactor.insertImage(imageUrl, ERROR);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                interactor.insertImage(imageUrl, INSERTED);
                return false;
            }
        };
    }

}
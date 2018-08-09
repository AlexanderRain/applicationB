package com.example.b.activity.presenters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
        try {
            switch (imageStatus) {
                case DEFAULT:
                    //  Alexander Rain: this case for links, comes from test fragment
                    interactor.insertImage(imageUrl, imageStatus);
                    break;

                case INSERTED:
                    //  Alexander Rain: this case for successful loaded links
                    break;

                default:
                    // Alexander Rain: this case for UNDEFINED and ERROR links
                interactor.updateImage(imageUrl, imageStatus);
            }
        }catch (Exception exception) {
            // Alexander Rain: exception appears when application started from launcher
            view.closeApp();
        }
    }
    public int checkForInternetConnection(String imageUrl) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connectivityManager.getActiveNetworkInfo();
        int status = ERROR;

        // Alexander Rain: may return null when there is no default network
        // In this case network connected
        if (network != null && network.isConnected()) {
            status = INSERTED;
            view.showImage(imageUrl);
            // In this case connection is not ot found, so status UNDEFINED
        } else if (network == null || !network.isConnected()) {
            status = UNDEFINED;

        }
        return status;
    }
}
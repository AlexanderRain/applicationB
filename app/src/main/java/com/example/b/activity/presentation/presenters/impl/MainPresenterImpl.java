package com.example.b.activity.presentation.presenters.impl;

import android.view.View;

import com.example.b.activity.domain.executor.Executor;
import com.example.b.activity.domain.executor.MainThread;
import com.example.b.activity.domain.interactors.SampleInteractor;
import com.example.b.activity.presentation.presenters.MainPresenter;
import com.example.b.activity.presentation.presenters.base.AbstractPresenter;

public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        SampleInteractor.Callback {

    private MainPresenter.View mView;

    public MainPresenterImpl(Executor executor,
                             MainThread mainThread,
                             View view) {
        super(executor, mainThread);
        mView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }
}
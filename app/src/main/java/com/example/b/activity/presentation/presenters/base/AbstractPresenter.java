package com.example.b.activity.presentation.presenters.base;

import com.example.b.activity.domain.executor.Executor;
import com.example.b.activity.domain.executor.MainThread;

public abstract class AbstractPresenter {
    protected Executor   mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}
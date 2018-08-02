package com.example.b.activity.domain.interactors.impl;

import com.example.b.activity.domain.executor.Executor;
import com.example.b.activity.domain.executor.MainThread;
import com.example.b.activity.domain.interactors.SampleInteractor;
import com.example.b.activity.domain.interactors.base.AbstractInteractor;
import com.example.b.activity.domain.repository.Repository;

public class SampleInteractorImpl extends AbstractInteractor implements SampleInteractor {

    private SampleInteractor.Callback mCallback;
    private Repository                mRepository;

    public SampleInteractorImpl(Executor threadExecutor,
                                MainThread mainThread,
                                Callback callback, Repository repository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mRepository = repository;
    }

    @Override
    public void run() {

    }
}
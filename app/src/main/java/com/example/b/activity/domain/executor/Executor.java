package com.example.b.activity.domain.executor;

import com.example.b.activity.domain.interactors.base.AbstractInteractor;

public interface Executor {

    void execute(final AbstractInteractor interactor);
}
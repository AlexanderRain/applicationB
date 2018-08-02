package com.example.b.activity.domain.executor;

public interface MainThread {
    void post(final Runnable runnable);
}
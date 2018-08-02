package com.example.b.activity.presentation.ui;

public interface BaseView {

    void showProgress();

    void hideProgress();

    void showError(String message);
}
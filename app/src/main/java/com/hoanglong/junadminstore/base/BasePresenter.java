package com.hoanglong.junadminstore.base;

public interface BasePresenter<T> {
    void setView(T view);

    void onStart();

    void onStop();
}

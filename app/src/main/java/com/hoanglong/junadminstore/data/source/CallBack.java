package com.hoanglong.junadminstore.data.source;

public interface CallBack<T> {
    void getDataSuccess(T data);

    void getDataError(String error);
}
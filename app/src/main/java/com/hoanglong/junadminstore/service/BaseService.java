package com.hoanglong.junadminstore.service;

import android.text.TextUtils;

import com.hoanglong.junadminstore.utils.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseService {

    public static IApiService getService() {
        return getRetrofit("").create(IApiService.class);
    }

    public static IApiService getService(String yourDomain) {
        return getRetrofit(yourDomain).create(IApiService.class);
    }

    private static Retrofit getRetrofit(String yourDomain) {
        System.setProperty("http.keepAlive", "false");
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit;

        if (yourDomain != null && !TextUtils.isEmpty(yourDomain)) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(yourDomain)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        } else {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}


package com.example.android_todes.apieventos;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientEvento {
    private static Retrofit retrofit;
    public static Retrofit getClientEvento()
    {
        retrofit =new Retrofit.Builder()
                .baseUrl("https://proyectotodes-production.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}

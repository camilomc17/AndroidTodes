package com.example.android_todes.apinoticias;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    public static Retrofit getClient()
    {
        retrofit =new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit;
    }
}

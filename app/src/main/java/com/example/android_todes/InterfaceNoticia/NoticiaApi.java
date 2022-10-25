package com.example.android_todes.InterfaceNoticia;

import com.example.android_todes.Model_noticia.Activity_noticia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NoticiaApi {

    @GET("noticias")
    Call<List<Activity_noticia>> getPosts();
}

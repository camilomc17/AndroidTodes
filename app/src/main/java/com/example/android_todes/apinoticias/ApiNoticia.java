package com.example.android_todes.apinoticias;

import com.example.android_todes.models.Noticia_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiNoticia {
    @GET("api/noticias")
    Call<List<Noticia_model>> getNoticias();

}

package com.example.android_todes.apieventos;

import com.example.android_todes.models.Evento_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEvento {
    @GET("api/eventos")
    Call<List<Evento_model>> obtenerListaEventos();
}

package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_todes.InterfaceNoticia.NoticiaApi;
import com.example.android_todes.Model_noticia.Activity_noticia;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityNoticias extends AppCompatActivity {


     TextView tvNombre_noticia;
     TextView tvDescripcion_noticia;
     TextView tvLugar_noticia;
     TextView tvResponsable_noticia;
     ImageView tvRuta_noticia;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_noticias);
        tvNombre_noticia=findViewById(R.id.tvNombre_noticia);
        tvDescripcion_noticia=findViewById(R.id.tvDescripcion_noticia);
        tvLugar_noticia=findViewById(R.id.tvLugar_noticia);
        tvResponsable_noticia=findViewById(R.id.tvResponsable_noticia);
        tvRuta_noticia=findViewById(R.id.imgNoticia);

    }

    public void getPosts()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8000/api")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NoticiaApi noticiaApi = retrofit.create(NoticiaApi.class);
        Call<List<Activity_noticia>> call = noticiaApi.getPosts();
        call.enqueue(new Callback<List<Activity_noticia>>() {
            @Override
            public void onResponse(Call<List<Activity_noticia>> call, Response<List<Activity_noticia>> response) {

                if(!response.isSuccessful())
                {

                }

            }

            @Override
            public void onFailure(Call<List<Activity_noticia>> call, Throwable t) {

            }
        });
    }


}
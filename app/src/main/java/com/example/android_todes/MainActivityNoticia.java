package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android_todes.apinoticias.ApiClient;
import com.example.android_todes.apinoticias.ApiNoticia;
import com.example.android_todes.models.Noticia_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityNoticia extends AppCompatActivity {
    //almacenar el listado de noticias json
    private List<Noticia_model> noticiaModels;
    //recycleView
    private RecyclerView recyclerView;
    //esta para utilizar nuestro Adapter
    private NoticiasAdapter noticiasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_noticia);

        recyclerView=findViewById(R.id.rv_noticias);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));


        obtenerNoticias();
    }


    public void obtenerNoticias() {
        Call<List<Noticia_model>> call= ApiClient.getClient().create(ApiNoticia.class).obtenerListaNoticias();
        call.enqueue(new Callback<List<Noticia_model>>() {
            @Override
            public void onResponse(Call<List<Noticia_model>> call, Response<List<Noticia_model>> response) {
                if(response.isSuccessful()) {
                    noticiaModels=response.body();
                    noticiasAdapter=new NoticiasAdapter(noticiaModels,getApplicationContext());
                    recyclerView.setAdapter(noticiasAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Noticia_model>> call, Throwable t) {
                Toast.makeText(MainActivityNoticia.this,"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void IrEvento(View view)
    {
        Intent ir = new Intent(MainActivityNoticia.this,MainActivityEvento.class);
        startActivity(ir);
    }
}
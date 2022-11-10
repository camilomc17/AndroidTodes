package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android_todes.apieventos.ApiClientEvento;
import com.example.android_todes.apieventos.ApiEvento;
import com.example.android_todes.models.Evento_model;
import com.example.android_todes.models.Noticia_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityEvento extends AppCompatActivity {

    //almacenar el listado de noticias json
    private List<Evento_model> eventoModels;
    //recycleView
    private RecyclerView recyclerView;
    //esta para utilizar nuestro Adapter
    private EventosAdapter eventosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_evento);

        recyclerView=findViewById(R.id.rv_eventos);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));


        obtenerEventos();
    }
    public void obtenerEventos() {
        Call<List<Evento_model>> call = ApiClientEvento.getClientEvento().create(ApiEvento.class).obtenerListaEventos();
        call.enqueue(new Callback<List<Evento_model>>() {
            @Override
            public void onResponse(Call<List<Evento_model>> call, Response<List<Evento_model>> response) {
                if(response.isSuccessful()) {
                    eventoModels=response.body();
                    eventosAdapter=new EventosAdapter(eventoModels,getApplicationContext());
                    recyclerView.setAdapter(eventosAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Evento_model>> call, Throwable t) {
                Toast.makeText(MainActivityEvento.this,"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void IrNoticia(View view)
    {
        Intent ir = new Intent(MainActivityEvento.this,MainActivityNoticia.class);
        startActivity(ir);
    }

}
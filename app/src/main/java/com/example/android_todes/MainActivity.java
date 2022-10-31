package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.android_todes.apinoticias.ApiClient;
import com.example.android_todes.apinoticias.ApiNoticia;
import com.example.android_todes.models.Noticia_model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Noticia_model> noticiaModels; //se encarga de almacenar el listado gson
    private RecyclerView recyclerView; //accede al contexto de Activyti_main que muestra el reciclevview
    private NoticiasAdapter noticiasAdapter;//para utilizar nuestro adaptador

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rv_noticias);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        showNoticias();
    }

    public void showNoticias()
    {
        Call<List<Noticia_model>> call = ApiClient.getClient().create(ApiNoticia.class).getNoticias();
        call.enqueue(new Callback<List<Noticia_model>>() {


            @Override //este sirve para recuparar el gson y psarlo al adapter
            public void onResponse(Call<List<Noticia_model>> call, Response<List<Noticia_model>> response) {
                if(response.isSuccessful())
                {
                    noticiaModels=response.body();
                    noticiasAdapter=new NoticiasAdapter(noticiaModels,getApplicationContext());
                    recyclerView.setAdapter(noticiasAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Noticia_model>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
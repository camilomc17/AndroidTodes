package com.example.android_todes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android_todes.apinoticias.ApiClient;
import com.example.android_todes.apinoticias.ApiNoticia;
import com.example.android_todes.models.Noticia_model;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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


    FloatingActionMenu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_noticia);

        actionMenu = (FloatingActionMenu) findViewById(R.id.MenuPrincipal);
        actionMenu.setClosedOnTouchOutside(true);

        recyclerView=findViewById(R.id.rv_noticias);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));


        obtenerNoticias();
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation_noticia);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.phone_admin:
                    String cellAdmin = "+57-";
                    Intent llamada_admin = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", cellAdmin, null));
                    startActivity(llamada_admin);
                    return true;
                case R.id.phone_ambulance:
                    String cellAmbulance = "+57-23";
                    Intent llamada_ambulance = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", cellAmbulance, null));
                    startActivity(llamada_ambulance);
                    return true;
                case R.id.phone_emergency:
                    String cellEmergency = "+57-911";
                    Intent llamada_emergency = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", cellEmergency, null));
                    startActivity(llamada_emergency);
                    return true;
            }

            return false;
        }
    };
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

    // Metodos de los botones
    public void IrEventos(View view) {
        Intent intentE=new Intent(this,MainActivityEvento.class);
        startActivity(intentE);
    }
    public void IrMiPerfil(View view){
        Intent ir_perfil = new Intent(this,MainActivityEvento.class);
        startActivity(ir_perfil);
        finish();
    }

    public void IrCrearIncidencia(View view){
        Intent intentE = new Intent(this,categoriasIncidencia.class);
        startActivity(intentE);


    }
    public void IrMisIncidencias(View view){


    }
    public void IrAyuda(){

    }
    public void CerrarSesion(){

    }






}
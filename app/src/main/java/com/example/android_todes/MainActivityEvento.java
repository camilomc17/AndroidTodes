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

import com.example.android_todes.apieventos.ApiClientEvento;
import com.example.android_todes.apieventos.ApiEvento;
import com.example.android_todes.models.Evento_model;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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


    FloatingActionMenu actionMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_evento);


        actionMenu = (FloatingActionMenu) findViewById(R.id.MenuPrincipal);
        actionMenu.setClosedOnTouchOutside(true);

        recyclerView=findViewById(R.id.rv_eventos);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));


        obtenerEventos();
        BottomNavigationView navigationViews = findViewById(R.id.bottom_navigation_evento);
        navigationViews.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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


    public void IrMiPerfil(){

    }

    public void IrCrearIncidencia(View view){
        Intent intentE=new Intent(this,FormActivityDenuncia.class);
        startActivity(intentE);


    }
    public void IrMisIncidencias(View view){


    }
    public void IrAyuda(){

    }
    public void CerrarSesion(){

    }

    public void IrNoticia(View view)
    {
        Intent ir = new Intent(this,MainActivityNoticia.class);
        startActivity(ir);
    }

}
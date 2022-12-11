package com.example.android_todes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_todes.apieventos.ApiClientEvento;
import com.example.android_todes.apieventos.ApiEvento;
import com.example.android_todes.models.Evento_model;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityEventoInvitado extends AppCompatActivity {

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
        setContentView(R.layout.activity_evento_invitado);

        actionMenu = (FloatingActionMenu) findViewById(R.id.MenuPrincipal);
        actionMenu.setClosedOnTouchOutside(true);

        recyclerView=findViewById(R.id.rv_eventos);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));


        obtenerEventos();
        BottomNavigationView navigationViews = findViewById(R.id.bottom_navigation_evento_invitado);
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
                Toast.makeText(ActivityEventoInvitado.this,"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Noticia_event(View view){
        Intent ir_noticia= new Intent(this,ActivityNoticiaInvitado.class);
        startActivity(ir_noticia);
        finish();
    }

    public void inicioSesion_event(View view){
        Intent ir = new Intent(this,InicioSesion.class);
        startActivity(ir);
        finish();
    }
    public void IrAyuda_invitado(View view){
        Intent irAyudaInvitado = new Intent(this,MainActivityOpcionMenuInvitado.class);
        startActivity(irAyudaInvitado);
        finish();
    }
}
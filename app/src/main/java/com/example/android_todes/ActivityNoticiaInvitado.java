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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android_todes.apinoticias.ApiClient;
import com.example.android_todes.apinoticias.ApiNoticia;
import com.example.android_todes.models.Noticia_model;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityNoticiaInvitado extends AppCompatActivity {

    //almacenar el listado de noticias json
    private List<Noticia_model> noticiaModels;
    //recycleView
    private RecyclerView recyclerView;
    //esta para utilizar nuestro Adapter
    private NoticiasAdapter noticiasAdapter;

    private static String url ="http://127.0.0.1:8000/api/eventos";

    FloatingActionMenu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_invitado);
        actionMenu = (FloatingActionMenu) findViewById(R.id.MenuPrincipal);
        actionMenu.setClosedOnTouchOutside(true);


        recyclerView=findViewById(R.id.rv_noticias);
        //recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noticiaModels = new ArrayList<>();

        obtenerNoticias();
        //cargarNoticias();
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
                Toast.makeText(ActivityNoticiaInvitado.this,"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Metodos de los botones
    public void IrCrearReporteInvitado(View view) {
        Intent intentIncidenciaInvitado = new Intent(this,categoriasIncidenciaInvitado.class);
       startActivity(intentIncidenciaInvitado);
        finish();
    }

    public void IrInicioSesion_invitado(View view){
        Intent ir = new Intent(this,InicioSesion.class);
        startActivity(ir);

    }
    /*public void IrAyuda_invitado(View view){
        Intent irAyudaInvitado = new Intent(this,MainActivityOpcionMenuInvitado.class);
        startActivity(irAyudaInvitado);
        finish();
    }*/
    public void IrEventos_invitado(View view){
        Intent irAyudaInvitado = new Intent(this,ActivityEventoInvitado.class);
        startActivity(irAyudaInvitado);
        finish();
    }

    private void cargarNoticias(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response != null){
                            Toast.makeText(ActivityNoticiaInvitado.this,"si hay noticias",Toast.LENGTH_SHORT).show();
                        }

                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++)
                            {
                                JSONObject NoticiasJson = array.getJSONObject(i);/*
                                noticiaModels.add( new Noticia_model(
                                        NoticiasJson.getString("nombre_publicacion"),
                                        NoticiasJson.getString("descripcion_publicacion"),
                                        NoticiasJson.getString("lugar"),
                                        NoticiasJson.getString("fecha_y_hora"),
                                        NoticiasJson.getString("responsable"),
                                        NoticiasJson.getString("estado"),
                                        NoticiasJson.getString("tipo"),
                                        NoticiasJson.getString("ruta_archivo"),
                                        NoticiasJson.getString("imagen")
                                        ));*/
                            }
                            NoticiasAdapter adapter = new NoticiasAdapter(noticiaModels, getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                    Toast.makeText(ActivityNoticiaInvitado.this,"No hay noticias",Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }


}
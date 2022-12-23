package com.example.android_todes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_todes.models.Incidencias_model;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivityListMisIncidencias extends AppCompatActivity {
    private ArrayList<Incidencias_model> incidencias_modelList = new ArrayList<>();
    //recycleView
    private RecyclerView recyclerView;
    //esta para utilizar nuestro Adapter
    private TextView estados,dates,descripcions,barrios,edades,horas,nombs,ubicacions;
    private IncidenciasAdapter incidenciasAdapter;
    DatabaseReference database;
    FloatingActionMenu actionMenu;

    private FirebaseAuth firebaseAuth;
    static String idU;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_mis_incidencias);

        firebaseAuth = FirebaseAuth.getInstance();
        idU=firebaseAuth.getUid();
        actionMenu = (FloatingActionMenu) findViewById(R.id.MenuPrincipal_Incidencias);
        actionMenu.setClosedOnTouchOutside(true);

       recyclerView=findViewById(R.id.rv_mis_incidencias);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

       /* incidenciasAdapter = new IncidenciasAdapter(incidencias_modelList);
        recyclerView.setAdapter(incidenciasAdapter);*/
       database = FirebaseDatabase.getInstance().getReference();
        getIncidencias();


        BottomNavigationView navigationViews = findViewById(R.id.bottom_navigation_mis_incidencias);
        navigationViews.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.phone_admin:
                    String cellAdmin = "+57-3103557789";
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

    private void getIncidencias() {
        database.child("Incidencias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //incidencias_modelList.clear();

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String idUsuario = ds.child("IdUsuario").getValue().toString();
                        if (idUsuario.equals(idU)) {
                            String estado = ds.child("estado").getValue().toString();
                            String date = ds.child("date").getValue().toString();
                            String descripcion = ds.child("descripcion").getValue().toString();
                            String barrio = ds.child("barrio").getValue().toString();
                            String edad = ds.child("edad").getValue().toString();
                            String hora = ds.child("hora").getValue().toString();
                            String nombres = ds.child("nombres").getValue().toString();
                            String ubicacion = ds.child("ubicacion").getValue().toString();
                            String urlimagen = ds.child("urlimagen").getValue().toString();

                            incidencias_modelList.add(new Incidencias_model(idUsuario, barrio, date, descripcion, edad, estado, hora, nombres, ubicacion, urlimagen));

                        }
                        incidenciasAdapter = new IncidenciasAdapter(incidencias_modelList, R.layout.campos_mis_incidencias);
                        recyclerView.setAdapter(incidenciasAdapter);
                    }


                } else {
                    Toast.makeText(MainActivityListMisIncidencias.this, "No tiene incidencias", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivityListMisIncidencias.this, "NO SE RESIVIERON LOS DATOS", Toast.LENGTH_SHORT).show();
            }
        });

    }


    // Metodos de los botones
    public void IrEventosInci(View view) {
        Intent intentEventos=new Intent(this,MainActivityEvento.class);
        startActivity(intentEventos);
        finish();
    }
    public void IrMiPerfil(View view){
        Intent intentE=new Intent(this,ActivityMiCuenta.class);
        startActivity(intentE);

    }

    public void IrCrearIncidencia(View view){
        Intent intentE = new Intent(this,categoriasIncidencia.class);
        startActivity(intentE);
        finish();
    }
    public void IrNoticiasInci(View view)
    {
        Intent ir = new Intent(this,MainActivityNoticia.class);
        startActivity(ir);
        finish();
    }
    public void IrAyuda(View view){
        Intent intentE = new Intent(this,MainActivityOpcionMenuRegistrado.class);
        startActivity(intentE);

    }
    public void cerrarSesionIncidencia(View view){
        firebaseAuth.signOut();
        Toast.makeText(this,"Se ha cerrado sesion",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,InicioSesion.class));
        finish();

    }
}






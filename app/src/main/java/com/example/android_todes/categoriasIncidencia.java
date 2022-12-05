package com.example.android_todes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class categoriasIncidencia extends AppCompatActivity {

Button pasaMain;
    ImageView abuso;
    ImageView genero;
    ImageView intrafamily;
    ImageView fisico;
    ImageView discriminacion;
    ImageView igualdad;

    FloatingActionMenu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias_incidencia);

        abuso = findViewById(R.id.imageViewAbuso);
        genero = findViewById(R.id.imageViewGenero);
        intrafamily = findViewById(R.id.imageIntrafamiliar);
        fisico = findViewById(R.id.imageViewFisico);
        discriminacion = findViewById(R.id.imageViewDiscriminacion);
        igualdad = findViewById(R.id.imageViewIgualdad);

      /*  actionMenu = (FloatingActionMenu) findViewById(R.id.MenuPrincipal);
        actionMenu.setClosedOnTouchOutside(true);*/

        BottomNavigationView navigationViews = findViewById(R.id.bottom_navigation_incidencia);
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

    /*public void IrMiPerfil(){

    }


    public void IrMisIncidencias(View view){


    }
    public void IrAyuda(View view){
        Intent irAyudaRegistrado = new Intent(this,MainActivityOpcionMenuRegistrado.class);
        startActivity(irAyudaRegistrado);
    }
    public void CerrarSesion(){

    }
    public void IrEvento(View view)
    {
        Intent ir = new Intent(this,MainActivityEvento.class);
        startActivity(ir);
    }

    public void IrNoticia(View view)
    {
        Intent ir = new Intent(this,MainActivityNoticia.class);
        startActivity(ir);
    }
*/
    public void pasaUbicacion (View view){
        Intent atrasMenu= new Intent(categoriasIncidencia.this,MapsUbicacion.class);
        startActivity(atrasMenu);

    }





}
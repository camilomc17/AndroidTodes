package com.example.android_todes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class categoriasIncidenciaInvitado extends AppCompatActivity {

    ImageView abuso_invitado;
    ImageView genero_invitado;
    ImageView intrafamily_invitado;
    ImageView fisico_invitado;
    ImageView discriminacion_invitado;
    ImageView igualdad_invitado;

    FloatingActionMenu actionMenu_invitado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias_incidencia_invitado);



        fisico_invitado=findViewById(R.id.imageViewFisico_invitado);
        genero_invitado=findViewById(R.id.imageViewGenero_invitado);
        abuso_invitado=findViewById(R.id.imageViewAbuso_invitado);
        discriminacion_invitado=findViewById(R.id.imageViewDiscriminacion_invitado);
        igualdad_invitado=findViewById(R.id.imageViewIgualdad_invitado);
        intrafamily_invitado=findViewById(R.id.imageIntrafamiliar_invitado);

        actionMenu_invitado = (FloatingActionMenu) findViewById(R.id.MenuPrincipal);
        actionMenu_invitado.setClosedOnTouchOutside(true);

        BottomNavigationView navigationViews = findViewById(R.id.bottom_navigation_incidencia_invitado);
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

    public void CerrarSesion(){

    }
    public void IrEvento(View view)
    {
        Intent ir = new Intent(this,MainActivityEvento.class);
        startActivity(ir);
    }*/
    public void IrInicioSesionInvitado(View view){
        Intent ir = new Intent(this,InicioSesion.class);
        startActivity(ir);
    }
    public void IrNoticia(View view)
    {
        Intent ir = new Intent(this,ActivityNoticiaInvitado.class);
        startActivity(ir);
    }
    public void IrAyuda(View view){
        Intent irAyudaInvitado = new Intent(this,MainActivityOpcionMenuInvitado.class);
        startActivity(irAyudaInvitado);
    }
    public void pasaUbicacion (View view){
        Intent atrasMenu= new Intent(categoriasIncidenciaInvitado.this,MapsUbicacionInvitado.class);
        startActivity(atrasMenu);

    }

}
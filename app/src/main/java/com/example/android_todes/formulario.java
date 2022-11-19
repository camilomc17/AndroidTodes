package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class formulario extends AppCompatActivity {
TextView dirr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);



        dirr=findViewById(R.id.textFormulario);



        Bundle recibeDatos=getIntent().getExtras();
        String info=recibeDatos.getString("KeyD");
        dirr.setText(info);



       // Bundle recibeDatos=getIntent().getExtras();
        //String info=recibeDatos.getString("KeyDatos");
        //dirr.setText(info);








    }




    public void volverAMapa (View view){
        Intent regresoMapas= new Intent(formulario.this,categoriasIncidencia.class);
        startActivity(regresoMapas);

    }




        }











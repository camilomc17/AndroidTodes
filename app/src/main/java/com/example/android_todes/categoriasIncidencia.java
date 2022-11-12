package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class categoriasIncidencia extends AppCompatActivity {

Button pasaMain;
    ImageView abuso;
    ImageView genero;
    ImageView intrafamily;
    ImageView fisico;
    ImageView discriminacion;
    ImageView igualdad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias_incidencia);

        pasaMain= findViewById(R.id.buttonRegresaMain);

        abuso = findViewById(R.id.imageViewAbuso);
        genero = findViewById(R.id.imageViewGenero);
        intrafamily = findViewById(R.id.imageIntrafamiliar);
        fisico = findViewById(R.id.imageViewFisico);
        discriminacion = findViewById(R.id.imageViewDiscriminacion);
        igualdad = findViewById(R.id.imageViewIgualdad);


     
    }
    public void pasaMapa (View view){
        Intent atrasMenu= new Intent(categoriasIncidencia.this,MapsUbicacion.class);
        startActivity(atrasMenu);

    }

    public void RegresoMain (View view){
        Intent atrasMenu= new Intent(categoriasIncidencia.this,MainActivity.class);
        startActivity(atrasMenu);

    }
}
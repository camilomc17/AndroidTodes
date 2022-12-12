package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivityOpcionMisIncidencias extends AppCompatActivity {
    ImageButton anterior;
    ImageButton siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_opcion_mis_incidencias);
        siguiente=findViewById(R.id.imageButtonMI);
        anterior=findViewById(R.id.imageButtonMI2);
    }
    public void Siguiente(View view){
        Intent pasar=new Intent(this,MainActivityOpcionLlamadas.class);
        startActivity(pasar);
    }
    public void Anterior(View view){
        Intent antes= new Intent(this,MainActivityCrearIncidencia.class);
        startActivity(antes);
    }
}
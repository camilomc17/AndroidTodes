package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivityCrearIncidencia extends AppCompatActivity {
    ImageButton siguiente;
    ImageButton anterior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_crear_incidencia);
        anterior=findViewById(R.id.imageButtonCR1);
        siguiente=findViewById(R.id.imageButtonCR2);
    }
    public void Siguiente(View view){
        Intent pasar=new Intent(this,MainActivityOpcionMisIncidencias.class);
        startActivity(pasar);
    }
    public void Anterior(View view){
        Intent antes=new Intent(this,MainActivityOpcionMenuRegistrado.class);
        startActivity(antes);
    }
}
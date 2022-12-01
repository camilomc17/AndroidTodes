package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivityOpcionLlamadas extends AppCompatActivity {
    ImageButton siguiente;
    ImageButton anterior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_opcion_llamadas);
        anterior=findViewById(R.id.imageButtonCR1);
        siguiente=findViewById(R.id.imageButtonCR2);
    }
    public void Siguiente(View view){
        Intent pasar=new Intent(this,MainActivityMiPerfil.class);
        startActivity(pasar);
    }
    public void Anterior(View view){
        Intent antes= new Intent(this,MainActivityOpcionMisIncidencias.class);
        startActivity(antes);
    }
}
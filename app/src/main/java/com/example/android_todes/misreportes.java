package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class misreportes extends AppCompatActivity {
    ImageButton anterior;
    ImageButton siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misreportes);
        siguiente=findViewById(R.id.imageButtonMR2);
        anterior=findViewById(R.id.imageButtonMR1);
    }
    public void Siguiente(View view){
        Intent pasar=new Intent(this,crearreporte.class);
        startActivity(pasar);
    }
    public void Anterior(View view){
        Intent antes= new Intent(this,eventos.class);
        startActivity(antes);
    }
}
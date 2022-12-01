package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivityOpcionEvenNoti extends AppCompatActivity {
    ImageButton anterior;
    ImageButton siguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_opcion_even_noti);
        siguiente=findViewById(R.id.imageButtonEN);
        anterior=findViewById(R.id.imageButtonEN2);
    }

    public void Siguiente(View view){
        Intent pasar=new Intent(this,MainActivityOpcionMenuInvitado.class);
        startActivity(pasar);
    }
    public void Anterior(View view){
        Intent antes=new Intent(this,MainActivity.class);
        startActivity(antes);
    }
}
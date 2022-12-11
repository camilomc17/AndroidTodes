package com.example.android_todes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityOpcionMenuInvitado extends AppCompatActivity {
    ImageButton siguiente;
    ImageButton anterior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_opcion_menu_invitado);
        anterior=findViewById(R.id.imageButtonCR1);
        siguiente=findViewById(R.id.imageButtonCR2);
    }
    public void Siguiente(View view){
        Intent pasar=new Intent(this,MainActivityCrearIncidencia.class);
        startActivity(pasar);
        finish();
    }
    public void Anterior(View view){
        Intent antes= new Intent(this,ActivityNoticiaInvitado.class);
        startActivity(antes);
        finish();
    }
}
package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.os.Bundle;

public class MainActivityMiPerfil extends AppCompatActivity {

    ImageButton devolver;
    ImageButton siguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mi_perfil);
    }
    public void Siguiente(View view){
        Intent pasar=new Intent(this,MainActivityOpcionEvenNoti.class);
        startActivity(pasar);
    }
    public void Anterior(View view){
        Intent antes=new Intent(this,MainActivity.class);
        startActivity(antes);
    }
}
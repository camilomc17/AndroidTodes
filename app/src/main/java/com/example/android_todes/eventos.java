package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class eventos extends AppCompatActivity {
    ImageButton anterior;
    ImageButton siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        siguiente=findViewById(R.id.imageBtnE1);
        anterior=findViewById(R.id.imageBtnE2);
    }
    public void Siguiente(View view){
        Intent pasar=new Intent(this,misreportes.class);
        startActivity(pasar);
    }
    public void Anterior(View view){
        Intent antes=new Intent(this,MainActivity.class);
        startActivity(antes);
    }
}
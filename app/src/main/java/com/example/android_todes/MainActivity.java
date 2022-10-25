package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void pasarCategorias (View view){
        Intent pasaCategorias= new Intent(MainActivity.this,categoriasIncidencia.class);
    startActivity(pasaCategorias);

    }
}
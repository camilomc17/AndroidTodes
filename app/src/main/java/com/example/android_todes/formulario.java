package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class formulario extends AppCompatActivity {
TextView dirr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        dirr=findViewById(R.id.textDirecFormul);







        Bundle recibeDatos=getIntent().getExtras();
        String info=recibeDatos.getString("KeyDatos");
        dirr.setText(info);

    }
}
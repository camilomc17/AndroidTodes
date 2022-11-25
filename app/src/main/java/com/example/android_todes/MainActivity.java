package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnseguir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnseguir=findViewById(R.id.btnSeguir);

    }

    public void Seguir(View view) {
        Intent intentE=new Intent(this,ActivityBotones.class);
        startActivity(intentE);
        finish();
    }
}
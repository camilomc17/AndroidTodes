package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Animation animacion1 , animacion2;
    ImageView logo;

    TextView descripcion , todestext;
    int SPLASH_TIME=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        animacion1 = AnimationUtils.loadAnimation(this,R.anim.animacion1);
        animacion2 = AnimationUtils.loadAnimation(this,R.anim.animacion2);
        todestext=findViewById(R.id.todestext);
        descripcion=findViewById(R.id.descripcion);
        logo=findViewById(R.id.logo);
        logo.setAnimation(animacion1);
        logo.setAnimation(animacion2);
        descripcion.setAnimation(animacion1);
        descripcion.setAnimation(animacion2);
        todestext.setAnimation(animacion1);
        todestext.setAnimation(animacion2);
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent paginadashboard=new Intent(MainActivity.this,NavigationDrawerMenu.class);
                startActivity(paginadashboard);
                finish();
            }

        },SPLASH_TIME);

    }

}

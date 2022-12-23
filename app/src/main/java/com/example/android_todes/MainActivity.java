package com.example.android_todes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(this,MainActivityNoticia.class));
            finish();
        }else{
            iniciarapp();
        }

    }
    public void iniciarapp() {
        Animation animacion1, animacion2;
        ImageView logo;
        TextView descripcion, todestext;
        int SPLASH_TIME = 5000;

        animacion1 = AnimationUtils.loadAnimation(this, R.anim.animacion1);
        animacion2 = AnimationUtils.loadAnimation(this, R.anim.animacion2);
        todestext = findViewById(R.id.todestext);
        descripcion = findViewById(R.id.descripcion);
        logo = findViewById(R.id.loguito);
        logo.setAnimation(animacion1);
        logo.setAnimation(animacion2);
        descripcion.setAnimation(animacion1);
        descripcion.setAnimation(animacion2);
        todestext.setAnimation(animacion1);
        todestext.setAnimation(animacion2);
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent paginadashboard = new Intent(MainActivity.this,ActivityNoticiaInvitado.class);
                startActivity(paginadashboard);
                finish();
            }

        }, SPLASH_TIME);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(this,MainActivityNoticia.class));
            finish();
        }
    }
}
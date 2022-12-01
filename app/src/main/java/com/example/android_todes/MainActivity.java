package com.example.android_todes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        iniciarapp();

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
                Intent paginadashboard = new Intent(MainActivity.this, MainActivityNoticia.class);
                startActivity(paginadashboard);
                finish();
            }

        }, SPLASH_TIME);

    }
}

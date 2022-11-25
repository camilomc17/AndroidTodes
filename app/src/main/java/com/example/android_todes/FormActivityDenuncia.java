package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FormActivityDenuncia extends AppCompatActivity {

    FloatingActionButton Btn_irgalery;
    FloatingActionButton Btn_ircamara;
    ImageView imagenIncidencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_denuncia);

        Btn_ircamara=findViewById(R.id.button_ir_camara);
        Btn_irgalery=findViewById(R.id.button_ir_galery);
        imagenIncidencia=findViewById(R.id.imagen_para_incidencia);

        Btn_ircamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });

        Btn_irgalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirGalery();
            }
        });

    }
     private void abrirGalery()
     {
         Intent galeria = new Intent();
         galeria.setAction(Intent.ACTION_VIEW);
         galeria.setType("image/*");
         if(galeria.resolveActivity(getPackageManager())!=null)
         {
             startActivityForResult(galeria, 1);
         }
     }

     private void abrirCamara()
     {
         Intent camara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         if(camara.resolveActivity(getPackageManager())!= null)
         {
             startActivityForResult(camara,1);
         }

     }

     protected void onActivityResult(int requestCode, int resultCode, Intent data) 
     {
         super.onActivityResult(requestCode, resultCode, data);
         if(requestCode==1 && resultCode==RESULT_OK)
         {
             Bundle extras = data.getExtras();
             Bitmap imgBitmap =(Bitmap) extras.get("data");
             imagenIncidencia.setImageBitmap(imgBitmap);
         }
     }    
}
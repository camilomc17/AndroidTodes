package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends AppCompatActivity {



    Button btnCamara;
    Button btnGaleria;
    ImageView imgView;
    private int requestCode;
    private int resultCode;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        btnCamara = findViewById(R.id.btncamara);
        btnGaleria = findViewById(R.id.btngaleria);
        imgView = findViewById(R.id.imageView);

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });
        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galeria();
            }
        });
    }

    private void galeria(){
        Intent galeria=new Intent();
        galeria.setAction(Intent.ACTION_VIEW);
        galeria.setType("image/*");
        if(galeria.resolveActivity(getPackageManager()) != null){
            startActivityForResult(galeria, 1);
        }

    }
    private void abrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 1);
        }
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1 && resultCode==RESULT_OK){
            Bundle extras=data.getExtras();
            Bitmap imgBitmap= (Bitmap) extras.get("data");
            imgView.setImageBitmap(imgBitmap);
        }
    }
}
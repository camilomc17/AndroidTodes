package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class FormActivityDenuncia extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton Btn_irgalery;
    FloatingActionButton Btn_ircamara;
    ImageView imagenIncidencia;
    EditText fecha;
    Button btnfecha;
    private int dia,mes,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_denuncia);

        Btn_ircamara=findViewById(R.id.button_ir_camara);
        Btn_irgalery=findViewById(R.id.button_ir_galery);
        imagenIncidencia=findViewById(R.id.imagen_para_incidencia);

        fecha = findViewById(R.id.TextFecha);
        btnfecha = findViewById(R.id.buttonFecha);
        btnfecha.setOnClickListener(this);

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
         @Override
           public void onClick(View view) {
        if(view == btnfecha){
            final Calendar C = Calendar.getInstance();
            dia = C.get(Calendar.DAY_OF_MONTH);
            mes = C.get(Calendar.MONTH);
            year = C.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int Year, int Month, int Day) {
                    fecha.setText(Day+"/"+Month+"/"+Year);
                }
            }
                    ,dia,mes,year);
            datePickerDialog.show();
        }
    }


    /*
    private void validation() {
        String nom=nombre.getText().toString();
        String ape=apellido.getText().toString();
        String contra=contraseña.getText().toString();
        String email1=email.getText().toString();
        if(nom.equals(""))
        {
            nombre.setError("required");
        }
        if(ape.equals(""))
        {
            apellido.setError("Escriba su apellido es Obligatorio");
        }
        if(contra.equals(""))
        {
            contraseña.setError("No ingreso su contaseña");
        }
        if(email1.equals(""))
        {
            email.setError("Ingrese su correo electronico");
        }
    }
    public void guardar(String nom,String ape,String contra,String email1)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("nombre",nom);
        map.put("apellido",ape);
        map.put("contraseña",contra);
        map.put("email",email1);

        myfirebase.collection("Users")
                .add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Usuario quedo Guardado", Toast.LENGTH_SHORT).show();
                    }
                });
    }
     */
}
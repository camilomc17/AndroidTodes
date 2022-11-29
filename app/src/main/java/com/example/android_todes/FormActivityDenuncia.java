package com.example.android_todes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.StringSearch;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;

public class FormActivityDenuncia extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton Btn_irgalery;
    FloatingActionButton Btn_ircamara;
    ImageView imagenIncidencia;
    EditText nombres_apellidos;
    EditText edad;
    EditText lugar_incidencia;
    EditText fecha_incidencia;
    EditText hora;
    EditText descripcion_incidencia;
    Button btn_send_diligencia;

    DatabaseReference databaseReference;
    ProgressDialog cargando;

    private FirebaseFirestore firestore;
    StorageReference storageReference;
    String storage_path = "imagesIncidencia/";
    private Uri image_url;
    String photo = "photo";
    String id;

    Button btnfecha;
    private int dia,mes,year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_denuncia);

        Btn_ircamara=findViewById(R.id.button_ir_camara);
        Btn_irgalery=findViewById(R.id.button_ir_galery);

        imagenIncidencia=findViewById(R.id.imagen_para_incidencia);
        nombres_apellidos=findViewById(R.id.editNombreYApellidos);
        edad=findViewById(R.id.editEdad);
        lugar_incidencia=findViewById(R.id.editLugarDiligencia);
        descripcion_incidencia=findViewById(R.id.Descripcion_de_incidencia);
        fecha_incidencia = findViewById(R.id.TextFecha);
        hora=findViewById(R.id.editHoraDiligencia);
        btn_send_diligencia=findViewById(R.id.enviarDiligencia);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        validation();
        guardarFormulario();


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
    private void validation() {
        String nom_apes=nombres_apellidos.getText().toString();
        String ed=edad.getText().toString();
        String lug=lugar_incidencia.getText().toString();
        String fech=fecha_incidencia.getText().toString();
        String hor=hora.getText().toString();
        String des=descripcion_incidencia.getText().toString();
        if(nom_apes.equals(""))
        {
            nombres_apellidos.setError("Ingrese nombre de la victima");
        }
        if(ed.equals(""))
        {
            edad.setError("Edad aproximada");
        }
        if(lug.equals(""))
        {
            lugar_incidencia.setError("Lugar");
        }
        if(fech.equals(""))
        {
            fecha_incidencia.setError("Fecha");
        }
        if(hor.equals(""))
        {
            hora.setError("hora");
        }
        if(des.equals(""))
        {
            descripcion_incidencia.setError("Ingrese los sucesos");
        }
    }

    private void guardarFormulario()
    {
        btn_send_diligencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_inci = nombres_apellidos.getText().toString();
                String lugar_inci = lugar_incidencia.getText().toString();
                String  fecha_inci = fecha_incidencia.getText().toString();
                String  descripcion_inci = descripcion_incidencia.getText().toString();


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
                    fecha_incidencia.setText(Day+"/"+Month+"/"+Year);
                }
            }
                    ,dia,mes,year);
            datePickerDialog.show();
        }
    }


    /*

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
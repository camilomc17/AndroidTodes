package com.example.android_todes;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android_todes.models.Incidencias;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
    Button btn_send_incidencia;

    DatabaseReference databaseReference;
    ProgressDialog cargando;

    FirebaseFirestore myfirestore;
    //StorageReference storageReference;
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

        nombres_apellidos=findViewById(R.id.editNombreYApellidos);
        edad=findViewById(R.id.editEdad);
        lugar_incidencia=findViewById(R.id.editLugarIncidencia);
        fecha_incidencia = findViewById(R.id.EditFechaIncidencia);
        hora=findViewById(R.id.editHoraIncidencia);
        descripcion_incidencia=findViewById(R.id.editDescripcionIncidencia);
        imagenIncidencia=findViewById(R.id.imagenIncidencia);
        btn_send_incidencia=findViewById(R.id.enviarIncidencia);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        myfirestore= FirebaseFirestore.getInstance();

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

        Bundle recibeDatos=getIntent().getExtras();
        String info=recibeDatos.getString("KeyD");
       lugar_incidencia.setText(info);

       /*databaseReference.child("Incidencias").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {

               for(final DataSnapshot snapshots : snapshot.getChildren()){
                   databaseReference.child("Incidencias").child(snapshots.getKey()).addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           Incidencias inci = snapshots.getValue(Incidencias.class);
                           String date = inci.getDate();
                           String descripcion = inci.getDescripcion();
                           String edad = inci.getEdad();
                           String hora = inci.getHora();
                           String nombres_apellidos = inci.getNombres_apellidos();
                           String ubicacion = inci.getUbicacion();
                           Log.e("FechaIncidencia:",""+date);
                           Log.e("DescripcionIncidencia:",""+descripcion);
                           Log.e("Edad:",""+edad);
                           Log.e("HoraIncidencia:",""+hora);
                           Log.e("Nombre:",""+nombres_apellidos);
                           Log.e("Ubicacion:",""+ubicacion);
                           Log.e("Datos:",""+snapshots.getValue());
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });

               }
           }
           @Override
           public void onCancelled(@NonNull DatabaseError error) {
           }
       });




*/

    }
    public void Enviar(View view)
    {
        String noms_apes= nombres_apellidos.getText().toString();
        String years=edad.getText().toString();
        String lugar_inci = lugar_incidencia.getText().toString();
        String fecha_inci = fecha_incidencia.getText().toString();
        String hora_inci = hora.getText().toString();
        String descripcion_inci = descripcion_incidencia.getText().toString();
        if(validation()) {
            enviarIncidencia(noms_apes, years, lugar_inci, fecha_inci, hora_inci, descripcion_inci);
            Toast.makeText(FormActivityDenuncia.this, "FORMULARIO SE ENVIO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(FormActivityDenuncia.this, "Falta ingresar datos", Toast.LENGTH_SHORT).show();

        }

    }
    public boolean validation()
    {
        boolean retorno=true;

        String noms_apes= nombres_apellidos.getText().toString();
        String years=edad.getText().toString();
        String lugar_inci = lugar_incidencia.getText().toString();
        String fecha_inci = fecha_incidencia.getText().toString();
        String hora_inci = hora.getText().toString();
        String descripcion_inci = descripcion_incidencia.getText().toString();

        if(noms_apes.isEmpty())
        {
            nombres_apellidos.setError("Ingrese nombre de la victima");
            retorno=false;
        }
        if(years.isEmpty())
        {
            edad.setError("Ingrese la edad aproximada");
            retorno=false;
        }
        if(lugar_inci.isEmpty())
        {
            lugar_incidencia.setError("Ingrese el lugar");
            retorno=false;
        }
        if(fecha_inci.isEmpty())
        {
            fecha_incidencia.setError("fecha de los hechos");
            retorno=false;
        }
        if(hora_inci.isEmpty())
        {
            hora.setError("Ingrese la hora");
            retorno=false;
        }
        if(descripcion_inci.isEmpty())
        {
            descripcion_incidencia.setError("Ingrese los sucesos");
            retorno=false;
        }
        return retorno;
    }
    private void enviarIncidencia(String noms_apes,String years, String lugar_inci, String fecha_inci, String hora_inci, String descripcion_inci)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("nombres_apellidos", noms_apes);
        map.put("edad", years);
        map.put("ubicacion", lugar_inci);
        map.put("date", fecha_inci);
        map.put("hora", hora_inci);
        map.put("descripcion", descripcion_inci);
        databaseReference.child("Incidencias").push().setValue(map);
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
}
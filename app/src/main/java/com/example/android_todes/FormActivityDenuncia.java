package com.example.android_todes;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FormActivityDenuncia extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton Btn_irgalery;
    FloatingActionButton Btn_ircamara;
    ImageView imagenIncidencia;
    EditText nombres_apellidos;
    EditText edad;
    EditText lugar_incidencia;
    EditText barrio_incidencia;
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

    DatePicker verPicker;
    Button btnfecha;
    Button btnhora;
    private int dia,mes,year,hour,minutos,rest;

private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker datePicker, int mount, int day, int years) {
        fecha_incidencia.setText(mount+day+years);
        refrescarFecha();
    }
};

    public void refrescarFecha() {
        String date= String.format(Locale.getDefault(),"%02d-%02d-%02d",mes+1,dia,year);
         fecha_incidencia.setText(date);
    }
/*    //esta para utilizar nuestro Adapter
   private IncidenciasAdapter incidenciasAdapter;

   private RecyclerView recyclerView;

    //almacenar el listado de noticias json
   private ArrayList<Incidencias_model> incidencias_models = new ArrayList<>();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_denuncia);
        Btn_ircamara=findViewById(R.id.button_ir_camara);
        Btn_irgalery=findViewById(R.id.button_ir_galery);

        verPicker=findViewById(R.id.id_dtPicker);
        nombres_apellidos=findViewById(R.id.editNombreYApellidos);
        edad=findViewById(R.id.editEdad);
        lugar_incidencia=findViewById(R.id.editLugarIncidencia);
        barrio_incidencia=findViewById(R.id.editBarrio);
        fecha_incidencia = findViewById(R.id.EditFechaIncidencia);
        hora=findViewById(R.id.editHoraIncidencia);
        descripcion_incidencia=findViewById(R.id.editDescripcionIncidencia);
        imagenIncidencia=findViewById(R.id.imagenIncidencia);
        btn_send_incidencia=findViewById(R.id.enviarIncidencia);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        myfirestore= FirebaseFirestore.getInstance();

     /*   recyclerView=findViewById(R.id.rv_mis_incidencias);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        //ObtenerDatosFirebase();*/
        btnhora=findViewById(R.id.buttonHora);
        btnhora.setOnClickListener(this);
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

    }

    /*public void ObtenerDatosFirebase()
    {
        databaseReference.child("Incidencias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(final DataSnapshot snapshots : snapshot.getChildren()){
                    databaseReference.child("Incidencias").child(snapshots.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Incidencias_model datas = snapshot.getValue(Incidencias_model.class);
                                incidencias_models.clear();
                                String date = datas.getDate();
                                String descripcion = datas.getDescripcion();
                                String edad = datas.getEdad();
                                String hora = datas.getHora();
                                String nombres_apellidos = datas.getNombres_apellidos();
                                String ubicacion = datas.getUbicacion();

                                incidencias_models.add(new Incidencias_model(date,descripcion,edad,hora,nombres_apellidos,ubicacion));
                                incidenciasAdapter = new IncidenciasAdapter(incidencias_models,getApplicationContext());
                                recyclerView.setAdapter(incidenciasAdapter);
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
    }*/

    public void Enviar(View view)
    {
        String noms_apes= nombres_apellidos.getText().toString();
        String years=edad.getText().toString();
        String lugar_inci = lugar_incidencia.getText().toString();
        String barrio_inci = barrio_incidencia.getText().toString();
        String fecha_inci = fecha_incidencia.getText().toString();
        String hora_inci = hora.getText().toString();
        String descripcion_inci = descripcion_incidencia.getText().toString();
        if(validation()) {
            enviarIncidencia(noms_apes, years, lugar_inci, barrio_inci, fecha_inci, hora_inci, descripcion_inci);
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
        String barrio_inci = barrio_incidencia.getText().toString();
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
        if(barrio_inci.isEmpty())
        {
            barrio_incidencia.setError("Ingrese el Barrio de la incidencia");
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
    private void enviarIncidencia(String noms_apes,String years, String lugar_inci,String barrio_inci, String fecha_inci, String hora_inci, String descripcion_inci)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("nombres_apellidos", noms_apes);
        map.put("edad", years);
        map.put("ubicacion", lugar_inci);
        map.put("barrio",barrio_inci);
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
            mes = C.get(Calendar.MONTH);
            dia = C.get(Calendar.DAY_OF_MONTH);
            year = C.get(Calendar.YEAR);

             refrescarFecha();
             btnfecha.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     DatePickerDialog dialog = new DatePickerDialog(FormActivityDenuncia.this,listener,mes,dia,year);
                     dialog.show();
                 }
             });

           /* DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int Year, int Month, int Day) {
                    fecha_incidencia.setText(Day+"/"+Month+"/"+Year);
                }
            }
                    ,dia,mes,year);
            datePickerDialog.show();*/
        }
        if(view== btnhora)
        {
            final Calendar C = Calendar.getInstance();
            hour = C.get(Calendar.HOUR_OF_DAY);
            minutos = C.get(Calendar.MINUTE);
            rest = Integer.parseInt(String.valueOf(C.get(Calendar.AM_PM)));

            TimePickerDialog timePickerDialog = new TimePickerDialog(FormActivityDenuncia.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                  hora.setText(hours+":"+minutes);
                }
            },hour,minutos,false);
        timePickerDialog.show();
        }
    }
}
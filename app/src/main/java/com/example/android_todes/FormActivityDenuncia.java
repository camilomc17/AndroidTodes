package com.example.android_todes;


import androidx.annotation.Nullable;
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


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

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
    private EditText fecha_incidencia;
    EditText hora;
    EditText descripcion_incidencia;
    Button btn_send_incidencia;


    DatabaseReference databaseReference;
    ProgressDialog cargando;


    FirebaseFirestore myfirestore;
    StorageReference storageReference; //para la img
    private static final int GALLERY_INTENT = 1;
    private static final int CAMARA_INTENT = 2;
    private int requestCode;
    private int resultCode;
    private Intent data;


    private Uri image_url_galeria;
    String photo = "photo";
    String id;

    DatePicker verPicker;
    //Button btnfecha;Button btnhora;
    private int year,mes,dia,hour,minutos,rest;

private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener()  {
    @Override
    public void onDateSet(DatePicker datePicker, int mount, int day, int years) {
      year = years;
      mes=mount;
      dia=day;
        //  fecha_incidencia.setText(years+"/"+(mount+1)+"/"+day);
        refrescarFecha();
    }
};


    public void refrescarFecha() {
        String date= String.format(Locale.getDefault(),"%02d-%02d-%02d",year,mes+1,dia);
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
        fecha_incidencia.setOnClickListener(this);
        hora=findViewById(R.id.editHoraIncidencia);
        hora.setOnClickListener(this);
        descripcion_incidencia=findViewById(R.id.editDescripcionIncidencia);
        imagenIncidencia=findViewById(R.id.imagenIncidencia);
        btn_send_incidencia=findViewById(R.id.enviarIncidencia);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        myfirestore= FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        cargando = new ProgressDialog(this);
     /*   recyclerView=findViewById(R.id.rv_mis_incidencias);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        //ObtenerDatosFirebase();*/
    /*    btnhora=findViewById(R.id.buttonHora);
        btnhora.setOnClickListener(this);
        btnfecha = findViewById(R.id.buttonFecha);
        btnfecha.setOnClickListener(this);*/
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
      /*          Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
               startActivityForResult(intent,GALLERY_INTENT);*/
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
        String url = image_url_galeria.toString();
        String estad = "no revisado";
        if(validation()) {
            enviarIncidencia(noms_apes, years, lugar_inci, barrio_inci, fecha_inci, hora_inci, descripcion_inci,url,estad);
            Toast.makeText(FormActivityDenuncia.this, "FORMULARIO SE ENVIO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            vaciarRegistro();
        }else{
            Toast.makeText(FormActivityDenuncia.this, "Falta ingresar datos", Toast.LENGTH_SHORT).show();


        }

    }

        private void vaciarRegistro() {

            nombres_apellidos.setText("");
            edad.setText("");
            lugar_incidencia.setText("");
            barrio_incidencia.setText("");
            fecha_incidencia.setText("");
            hora.setText("");
            descripcion_incidencia.setText("");
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
    private void enviarIncidencia(String noms_apes,String years, String lugar_inci,String barrio_inci, String fecha_inci, String hora_inci, String descripcion_inci,String url_galeria,String estado)
    {

        Map<String,Object> map = new HashMap<>();
        map.put("nombres_apellidos", noms_apes);
        map.put("edad", years);
        map.put("ubicacion", lugar_inci);
        map.put("barrio",barrio_inci);
        map.put("date", fecha_inci);
        map.put("hora", hora_inci);
        map.put("descripcion", descripcion_inci);
        map.put("url_imagen", url_galeria);
        map.put("estado",estado);

        databaseReference.child("Incidencias").push().setValue(map);
    }

     private void abrirGalery()
     {
         Intent galeria = new Intent();
         galeria.setAction(Intent.ACTION_PICK);
         galeria.setType("image/*");
         if(galeria.resolveActivity(getPackageManager())!=null)
         {
             startActivityForResult(galeria, GALLERY_INTENT);
         }
     }
     private void abrirCamara()
     {
         Intent camara = new Intent();
         camara.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
         if(camara.resolveActivity(getPackageManager())!= null)
         {
             startActivityForResult(camara,CAMARA_INTENT);
         }

     }




         @Override

           public void onClick(View view) {
        if(view == fecha_incidencia){
            final Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            mes = calendar.get(Calendar.MONTH);
            dia = calendar.get(Calendar.DAY_OF_MONTH);
            refrescarFecha();

             fecha_incidencia.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     DatePickerDialog dialog = new DatePickerDialog(FormActivityDenuncia.this,listener,year,mes,dia);
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
        if(view== hora) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
   if(requestCode==CAMARA_INTENT && resultCode==RESULT_OK){
       cargando.setTitle("SUBIENDO FOTO");
       cargando.setMessage("Subiendo foto al Formulario");
       cargando.setCancelable(false);
       cargando.show();
      Bundle extras = data.getExtras();
       Bitmap imgBitmap = (Bitmap) extras.get("data");
       imagenIncidencia.setImageBitmap(imgBitmap);
       cargando.dismiss();
       //StorageReference storecamara = storageReference.child("imagesIncidencia/").child(extras.);
   }
   if (requestCode==GALLERY_INTENT && resultCode==RESULT_OK) {
       cargando.setTitle("SUBIENDO FOTO");
       cargando.setMessage("Subiendo foto al Formulario");
       cargando.setCancelable(false);
       cargando.show();
       Uri url_img = data.getData();
       String url_image = url_img.toString();
       this.image_url_galeria=url_img;
       StorageReference storage_path = storageReference.child("imagesIncidencia/").child(url_image);
       storage_path.putFile(image_url_galeria).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
           @Override
           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

               cargando.dismiss();
               Picasso.with(FormActivityDenuncia.this)
                       .load(image_url_galeria)
                       .resize(100,200)
                       .into(imagenIncidencia);


               Toast.makeText(FormActivityDenuncia.this,"LA FOTO SE SUBIO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
           }
       });

   }

    }
}
package com.example.android_todes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrarseActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText apellido;
    private EditText correo;
    private EditText contrasena;
    FirebaseFirestore mifirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre=findViewById(R.id.nombre);
        apellido=findViewById(R.id.apellido);
        correo=findViewById(R.id.correo);
        contrasena=findViewById(R.id.contrasena);
        mifirebase = FirebaseFirestore.getInstance();
    }
    //para mirar menu

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        String nombre1 = nombre.getText().toString();
        String apellido1 = apellido.getText().toString();
        String correo1 = correo.getText().toString();
        String contrasena1 = contrasena.getText().toString();
        int num=item.getItemId();
        if (num==R.id.guardar){
            validacion();
            guardar(nombre1, apellido1, correo1,contrasena1);

        }
        return super.onOptionsItemSelected(item);
    }

    private void guardar(String nombre1, String apellido1, String correo1, String contrasena1) {
        Map<String, Object> map=new HashMap<>();
        map.put("nombre",nombre1);
        map.put("apellido",apellido1);
        map.put("correo",correo1);
        map.put("contrasena",contrasena1);
        mifirebase.collection("Personas").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"registro guarded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void validacion() {
        String nombre1 = nombre.getText().toString();
        String apellido1 = apellido.getText().toString();
        String correo1 = correo.getText().toString();
        String contrasena1 = contrasena.getText().toString();

        if (nombre1.equals("")) {
            nombre.setError("required");
        }
        if (apellido1.equals("")) {
            apellido.setError("required");
        }

        if (correo1.equals("")) {
            correo.setError("required");
        }

        if (contrasena1.equals("")) {
            contrasena.setError("required");
        }


    }
}
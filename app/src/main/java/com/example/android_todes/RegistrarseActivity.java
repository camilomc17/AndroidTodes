package com.example.android_todes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

    public class RegistrarseActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etNumero, etCorreo, etFechanacimiento, editTextAlias, etTelefono, etPassword, etPassword2;
    private CheckBox checkBox1, checkBox2;
    private Button btRegistrarse;

        //FirebaseFirestore AndroidTodes;


        private  String nombre1 = "";
        private  String apellido1 = "";
        private  String numero1 = "";
        private  String contrasena1 = "";
        private  String contrasena2 = "";
        private  String lista1 = "";
        private  String lista2 = "";
        private  String telefono = "";
        private  String correo1 = "";
        private  String alias = "";
        private  String fechanaci = "";

        private FirebaseAuth Auth;
        DatabaseReference Database;



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        etNombre=findViewById(R.id.etNombre);
        etApellido=findViewById(R.id.etApellido);
        etNumero=findViewById(R.id.etNumero);
        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        etTelefono=findViewById(R.id.etTelefono);
        etCorreo=findViewById(R.id.etCorreo);
        editTextAlias=findViewById(R.id.editTextAlias);
        etPassword=findViewById(R.id.etPassword);
        etPassword2=findViewById(R.id.etPassword2);
        etFechanacimiento=findViewById(R.id.etFechanacimiento);
        btRegistrarse=findViewById(R.id.btRegistrarse);

        Auth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance().getReference();


        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre1 = etNombre.getText().toString();
                apellido1 = etApellido.getText().toString();
                numero1 = etNumero.getText().toString();
                contrasena1 = etPassword.getText().toString();
                contrasena2 = etPassword2.getText().toString();
                lista1 = checkBox1.getText().toString();
                lista2 = checkBox2.getText().toString();
                telefono = etTelefono.getText().toString();
                correo1= etCorreo.getText().toString();
                alias= editTextAlias.getText().toString();
                fechanaci= etFechanacimiento.toString().toString();

                if (!nombre1.isEmpty() && !apellido1.isEmpty() && !numero1.isEmpty() &&contrasena1.isEmpty() &&contrasena2.isEmpty()
                && lista1.isEmpty() && lista2.isEmpty() && telefono.isEmpty() && correo1.isEmpty() && alias.isEmpty()
                && fechanaci.isEmpty()){

                    if (etPassword.length() >= 6){
                        registrarUser();
                    }else {
                        Toast.makeText(RegistrarseActivity.this,"contraseña debe tener al menos 6 caracteres" , Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(RegistrarseActivity.this,"faltas campos por diligenciar" , Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
        private void registrarUser() {
            Auth.createUserWithEmailAndPassword(correo1, contrasena1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        Map<String, Object> map = new HashMap<>();

                        map.put("nombre1", nombre1);
                        map.put("apellido1", apellido1);
                        map.put("numero1", numero1);
                        map.put("contrasena1", contrasena1);
                        map.put("contrasena2", contrasena2);
                        map.put("lista1", lista1);
                        map.put("lista2", lista2);
                        map.put("telefono", telefono);
                        map.put("correo1", correo1);
                        map.put("alias", alias);
                        map.put("fechanaci", fechanaci);

                        String id = Auth.getCurrentUser().getUid();


                        Database.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task2) {
                                if (task2.isSuccessful()){
                                    startActivity(new Intent(RegistrarseActivity.this, InicioSesion.class));
                                    finish();
                                }else{
                                    Toast.makeText(RegistrarseActivity.this, "no se creo usuario", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(RegistrarseActivity.this, "no se puede registrar", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


        public  void IniciarSesion(View view) {
            Intent i = new Intent(this,InicioSesion.class);
            startActivity(i);
        }
    }

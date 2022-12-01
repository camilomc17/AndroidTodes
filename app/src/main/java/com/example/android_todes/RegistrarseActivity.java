package com.example.android_todes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

    public class RegistrarseActivity extends AppCompatActivity {

        private EditText etCorreo, etPassword, etPassword2;
        private Button btGuardar;

        private String email = "";
        private String contrasena = "";
        private String confirmacionPass = "";

        private FirebaseAuth Auth;
        DatabaseReference Database;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registrarse);

            etCorreo = findViewById(R.id.etCorreo);
            etPassword = findViewById(R.id.etPassword);
            etPassword2 = findViewById(R.id.etPassword2);
            btGuardar = findViewById(R.id.btGuardar);

            Auth = FirebaseAuth.getInstance();
            Database = FirebaseDatabase.getInstance().getReference();

            btGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    email = etCorreo.getText().toString();
                    contrasena = etPassword.getText().toString();
                    confirmacionPass = etPassword2.getText().toString();

                    if (!email.isEmpty() && !contrasena.isEmpty() && !confirmacionPass.isEmpty()){

                        if (contrasena.length() >= 6){
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

            Auth.createUserWithEmailAndPassword(email, contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        Map<String, Object> map = new HashMap<>();
                        map.put("email", email);
                        map.put("contrasena", contrasena);
                        map.put("confirmacionPass", confirmacionPass);

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

        // public void onStart() {
        //  super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //  FirebaseUser currentUser = Auth.getCurrentUser();
        //updateUI(currentUser);
        //  }


        public  void InicioSesion(View view) {
            Intent i = new Intent(this,InicioSesion.class);
            startActivity(i);
        }




    }


package com.example.android_todes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InicioSesion extends AppCompatActivity {
    EditText etCorreo, etPassword;
    Button btIngresar;
    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        Auth = FirebaseAuth.getInstance();
        etCorreo = findViewById(R.id.etCorreo);
        etPassword = findViewById(R.id.etPassword);
        btIngresar = findViewById(R.id.btIngresar);

        btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etCorreo.getText().toString().trim();
                String contrasena = etPassword.getText().toString().trim();

                if (email.isEmpty() && contrasena.isEmpty()) {
                    Toast.makeText(InicioSesion.this, "ingrese datos", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, contrasena);
                }

            }
        });
    }
    private void loginUser(String email, String contrasena){
        Auth.signInWithEmailAndPassword(email, contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(InicioSesion.this, RegistrarseActivity.class));
                    Toast.makeText(InicioSesion.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(InicioSesion.this, "Error" , Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(InicioSesion.this, "usuario y/o contraseña errados", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public  void Registrarse(View view) {
        Intent i = new Intent(this,RegistrarseActivity.class);
        startActivity(i);
    }

    public void ActualizarPass(View view){
        Intent y = new Intent(this,ActualizarContrasena.class);
        startActivity(y);

    }
}
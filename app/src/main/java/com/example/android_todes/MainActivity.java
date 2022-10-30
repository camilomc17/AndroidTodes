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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText etCorreo;
    EditText etPassword;
    Button btIngresar;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCorreo = findViewById(R.id.etCorreo);
        etPassword = findViewById(R.id.etPassword);
        btIngresar = findViewById(R.id.btIngresar);
        firebaseAuth = FirebaseAuth.getInstance();

        btIngresar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String email = etCorreo.getText().toString();
            String password = etPassword.getText().toString();

            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    Toast.makeText(MainActivity.this, "ingreso exitoso", Toast.LENGTH_SHORT).show();
                    finish();
                }

            });

        }
    });




    }
    public void RegistarSesion(View view){
        Intent i = new Intent(this, RegistrarseActivity.class);
        startActivity(i);
    }
    public void IniciarSesion(View view){
        Intent i = new Intent(this, ActualizarContrasenaActivity.class);
        startActivity(i);
    }
}
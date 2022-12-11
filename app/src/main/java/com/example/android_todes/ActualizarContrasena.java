package com.example.android_todes;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseAuth;

public class ActualizarContrasena extends AppCompatActivity {

    private EditText etCorreo, etPassword;
    private Button btActualizar;
    private FirebaseAuth Auth;
    private ProgressDialog Dialog;

    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_contrasena);

        Auth = FirebaseAuth.getInstance();
        Dialog = new ProgressDialog(this);


        etCorreo = findViewById(R.id.etCorreo);
        etPassword = findViewById(R.id.etPassword);
        btActualizar = findViewById(R.id.btActualizar);

        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = etCorreo.getText().toString();
                if (!email.isEmpty()) {
                    Dialog.setMessage("Se enviara un enlace para reestablecer su contraseña");
                    Dialog.setCanceledOnTouchOutside(false);
                    Dialog.show();
                    actualizarPassword();
                }else {
                    Toast.makeText(ActualizarContrasena.this, "Para continuar debe ingresar correo", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }


    private void actualizarPassword(){
        Auth.setLanguageCode("es");
        Auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ActualizarContrasena.this, "Se envio a tu correo enlace para recuperar contraseña", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActualizarContrasena.this,InicioSesion.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(ActualizarContrasena.this, "No se pudo notificar solicitud al correo", Toast.LENGTH_SHORT).show();
                }

                Dialog.dismiss();
            }
        });

    }
    public void regresarLogin_password(View view){
        Intent returnofpassword = new Intent(ActualizarContrasena.this,InicioSesion.class);
        startActivity(returnofpassword);
        finish();

    }
}
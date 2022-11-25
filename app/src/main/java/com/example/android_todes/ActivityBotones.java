package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;

import java.util.Calendar;

public class ActivityBotones extends AppCompatActivity implements View.OnClickListener {

    FloatingActionMenu actionMenu;

    //Declaracion de variables
    TextView fecha;
    Button btnfecha;
    private int dia,mes,anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botones);


        actionMenu = (FloatingActionMenu) findViewById(R.id.MenuPrincipal);
        actionMenu.setClosedOnTouchOutside(true);

        //ReferenciarVariables-onCreate
        fecha = findViewById(R.id.tvfecha);
        btnfecha = findViewById(R.id.btnfecha);
        btnfecha.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == btnfecha){
            final Calendar C = Calendar.getInstance();
            dia = C.get(Calendar.DAY_OF_MONTH);
            mes = C.get(Calendar.MONTH);
            anio = C.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int Year, int Month, int Day) {
                    fecha.setText(Day+"/"+Month+"/"+Year);
                }
            }
                    ,dia,mes,anio);
            datePickerDialog.show();
        }
    }



    public void IrMiPerfil(){

    }

    public void IrCrearIncidencia(){

    }
    public void IrMisIncidencias(){

    }
    public void IrAyuda(){

    }
    public void CerrarSesion(){

    }

    public void IrEvento(View view) {
        Intent intentE=new Intent(this,ActivityBotones.class);
        startActivity(intentE);
        finish();
    }
}
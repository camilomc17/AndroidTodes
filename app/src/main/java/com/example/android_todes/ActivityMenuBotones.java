package com.example.android_todes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


public class ActivityMenuBotones extends AppCompatActivity {

    FloatingActionMenu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_botones);

        actionMenu = (FloatingActionMenu) findViewById(R.id.MenuPrincipal);
        actionMenu.setClosedOnTouchOutside(true);

    }

        public void IrMiPerfil(View view){
      Intent ir_perfil = new Intent(this,MainActivityEvento.class);
     startActivity(ir_perfil);
     finish();
        }

        public void IrCrearIncidencia(){

        }
        public void IrMisIncidencias(View view){
            Intent intentE=new Intent(this,FormActivityDenuncia.class);
            startActivity(intentE);
            finish();

        }
        public void IrAyuda(){

        }
        public void CerrarSesion(){

        }

        public void IrEvento(View view) {
            Intent intentE=new Intent(this,MainActivityEvento.class);
            startActivity(intentE);
            finish();
        }
}

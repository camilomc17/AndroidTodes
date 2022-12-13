package com.example.android_todes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_todes.models.Incidencias_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivityListMisIncidencias extends AppCompatActivity {
    private ArrayList<Incidencias_model> incidencias_modelList = new ArrayList<>();
    //recycleView
    private RecyclerView recyclerView;
    //esta para utilizar nuestro Adapter
    private IncidenciasAdapter incidenciasAdapter ;

    DatabaseReference database ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_mis_incidencias);

       recyclerView=findViewById(R.id.rv_mis_incidencias);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

       /* incidenciasAdapter = new IncidenciasAdapter(incidencias_modelList);
        recyclerView.setAdapter(incidenciasAdapter);*/
      database = FirebaseDatabase.getInstance().getReference();

    getIncidencias();
    }

    private void getIncidencias(){
       database.child("Incidencias").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if (snapshot.exists()){
                   incidencias_modelList.clear();
                   for (DataSnapshot ds :snapshot.getChildren()) {

                        String estado = ds.child("estado").getValue().toString();
                        String date=ds.child("date").getValue().toString();
                        String descripcion=ds.child("descripcion").getValue().toString();
                        String barrio=ds.child("barrio").getValue().toString();
                        String edad=ds.child("edad").getValue().toString();
                        String hora=ds.child("hora").getValue().toString();
                        String nombres=ds.child("nombres").getValue().toString();
                        String ubicacion=ds.child("ubicacion").getValue().toString();
                       //String urlimagen=ds.child("urlimagen").getValue().toString();

                        incidencias_modelList.add(new Incidencias_model());
                   }

                   incidenciasAdapter = new IncidenciasAdapter(incidencias_modelList, R.layout.activity_main_list_mis_incidencias);
                   recyclerView.setAdapter(incidenciasAdapter);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
    }

}




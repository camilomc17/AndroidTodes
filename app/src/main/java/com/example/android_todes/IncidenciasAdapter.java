package com.example.android_todes;


import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android_todes.models.Incidencias_model;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_todes.models.Incidencias_model;

import java.util.ArrayList;
import java.util.List;

public class IncidenciasAdapter extends RecyclerView.Adapter<IncidenciasAdapter.ViewHolder> {

    private ArrayList<Incidencias_model> incidencias_modelLista;
    private Context context;
    public IncidenciasAdapter(ArrayList<Incidencias_model> incidencias_modelLista, Context context) {
        this.incidencias_modelLista = incidencias_modelLista;
        this.context = context;
    }










    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.campos_mis_incidencias,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.edad.setText(incidencias_modelLista.get(position).getEdad());
        holder.date.setText(incidencias_modelLista.get(position).getDate());
        holder.descripcion.setText(incidencias_modelLista.get(position).getDescripcion());
        holder.hora.setText(incidencias_modelLista.get(position).getHora());
        holder.nombres_apellidos.setText(incidencias_modelLista.get(position).getNombres_apellidos());
        holder.ubicacion.setText(incidencias_modelLista.get(position).getUbicacion());
    }

    @Override
    public int getItemCount() {
        return incidencias_modelLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView date;
        private TextView descripcion;
        private TextView edad;
        private TextView hora;
        private TextView nombres_apellidos;
        private TextView ubicacion;

        public ViewHolder(View view){
            super(view);
            this.date =view.findViewById(R.id.id_text_fecha);
            this.descripcion = view.findViewById(R.id.id_text_descripcion);
            this.edad =  view.findViewById(R.id.id_text_edad);
            this.hora = view.findViewById(R.id.id_text_hora);
            this.nombres_apellidos =view.findViewById(R.id.id_text_nombre_apellido);
            this.ubicacion =view.findViewById(R.id.id_text_ubicacion);

        }
    }

}

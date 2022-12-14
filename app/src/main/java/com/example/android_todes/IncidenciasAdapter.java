package com.example.android_todes;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_todes.models.Incidencias_model;

import java.util.ArrayList;

public class IncidenciasAdapter extends RecyclerView.Adapter<IncidenciasAdapter.ViewHolder> {

    private ArrayList<Incidencias_model> incidencias_modelLista;
    private int resource;

    public IncidenciasAdapter(ArrayList<Incidencias_model> incidencias_modelLista, int resource) {
        this.incidencias_modelLista = incidencias_modelLista;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(resource,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.edad.setText(incidencias_modelLista.get(position).getEdad());
        holder.date.setText(incidencias_modelLista.get(position).getDate());
        holder.descripcion.setText(incidencias_modelLista.get(position).getDescripcion());
        holder.hora.setText(incidencias_modelLista.get(position).getHora());
        holder.nombres.setText(incidencias_modelLista.get(position).getNombres());
        holder.ubicacion.setText(incidencias_modelLista.get(position).getUbicacion());
        holder.barrio.setText(incidencias_modelLista.get(position).getBarrio());
        holder.estado.setText(incidencias_modelLista.get(position).getEstado());
    }

    @Override
    public int getItemCount() {
        return incidencias_modelLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

      //  private EditText IdUsuario;
        private TextView barrio;
        private TextView date;
        private TextView descripcion;
        private TextView edad;
        private TextView estado;
        private TextView hora;
        private TextView nombres;
        private TextView ubicacion;
       // private EditText urlimagen;

        public ViewHolder(View view){
            super(view);
            this.barrio =view.findViewById(R.id.id_text_barrio);
            this.date =view.findViewById(R.id.id_text_fecha);
            this.descripcion = view.findViewById(R.id.id_text_descripcion);
            this.edad =  view.findViewById(R.id.id_text_edad);
            this.estado =view.findViewById(R.id.id_text_estado);
            this.hora = view.findViewById(R.id.id_text_hora);
            this.nombres =view.findViewById(R.id.id_text_nombre_apellido);
            this.ubicacion =view.findViewById(R.id.id_text_ubicacion);


         //   this.estado =view.findViewById(R.id.id_text_estado);

        }
    }

}

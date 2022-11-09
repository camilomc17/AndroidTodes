package com.example.android_todes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_todes.models.Evento_model;
import com.example.android_todes.models.Noticia_model;

import java.util.List;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ViewHolder> {

    //guardara el listado de noticias
    private List<Evento_model> eventoModels;

    //permite acceder al activity_main que muestre el recicleView
    private Context context;

    public EventosAdapter(List<Evento_model> eventoModels, Context context) {
        this.eventoModels = eventoModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_eventos,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.descripcionEvento.setText(eventoModels.get(position).getDescripcion_publicacion());
       // holder.estadoEvento.setText(eventoModels.get(position).getEstado());
        holder.lugarEvento.setText(eventoModels.get(position).getLugar());
        holder.nombreEvento.setText(eventoModels.get(position).getNombre_publicacion());
        holder.responsableEvento.setText(eventoModels.get(position).getResponsable());
       // holder.tipoEvento.setText(eventoModels.get(position).getTipo());
        Glide.with(context).load(eventoModels.get(position).getRuta_archivo()).into(holder.rutaarchivoEvento);
    }

    @Override
    public int getItemCount() {
        return eventoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView rutaarchivoEvento;
        private TextView nombreEvento;
        private TextView descripcionEvento;
        private TextView lugarEvento;
        private TextView responsableEvento;
    /*    private TextView estadoEvento;
        private TextView tipoEvento;*/
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             rutaarchivoEvento=itemView.findViewById(R.id.image_eventos);
             nombreEvento=itemView.findViewById(R.id.tvNombre_evento);
             descripcionEvento=itemView.findViewById(R.id.tvDescripcion_evento);
             lugarEvento=itemView.findViewById(R.id.tvLugar_evento);
             responsableEvento=itemView.findViewById(R.id.tvResponsable_evento);
           /*  estadoEvento=itemView.findViewById(R.id.tvEstado_evento);
             tipoEvento=itemView.findViewById(R.id.tvTipo_evento);*/
        }
    }
}

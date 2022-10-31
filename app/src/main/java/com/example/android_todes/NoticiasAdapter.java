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
import com.example.android_todes.models.Noticia_model;

import java.util.List;

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {

    private List<Noticia_model> noticiaModels;  //se encarga de guardar el listado
    private Context context;      //accede al contexto de Activyti_main que muestra el reciclevview

    //inicializamos estos dos objetos
    public NoticiasAdapter(List<Noticia_model> noticiaModels, Context context) {
        this.noticiaModels = noticiaModels;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //especificamos el arch(item_movie.xml)
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_noticias,parent,false);

        return new ViewHolder(view);
    }

    //en este colocamos los componentes de item_moviexml
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.descripcion_publicacion.setText(noticiaModels.get(position).getDescripcion_publicacion());
        holder.nombre_publicacion.setText(noticiaModels.get(position).getNombre_publicacion());
        holder.lugar.setText(noticiaModels.get(position).getLugar());
        holder.responsable.setText(noticiaModels.get(position).getResponsable());
        Glide.with(context).load(noticiaModels.get(position).getRuta_archivo())
                .into(holder.ruta_archivo);
    }

    @Override
    public int getItemCount() {
        return noticiaModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ruta_archivo;
        private TextView nombre_publicacion;
        private TextView descripcion_publicacion;
        private TextView lugar;
        private TextView responsable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ruta_archivo= itemView.findViewById(R.id.image_noticias);
            nombre_publicacion= itemView.findViewById(R.id.tvNombre_noticia);
            descripcion_publicacion=itemView.findViewById(R.id.tvDescripcion_noticia);
            lugar=itemView.findViewById(R.id.tvLugar_noticia);
            responsable=itemView.findViewById(R.id.tvResponsable_noticia);

        }
    }
}

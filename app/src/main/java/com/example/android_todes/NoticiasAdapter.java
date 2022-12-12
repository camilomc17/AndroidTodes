package com.example.android_todes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.android_todes.models.Noticia_model;

import java.util.List;

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {

    //guardara el listado de noticias
     private List<Noticia_model> noticiaModels;

    //permite acceder al activity_main que muestre el recicleView
     private Context context;

    public NoticiasAdapter(List<Noticia_model> noticiaModels, Context context) {
        this.noticiaModels = noticiaModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext())
               .inflate(R.layout.item_noticias,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      holder.nombreNoticia.setText(noticiaModels.get(position).getNombre_publicacion());
      holder.descripcionNoticia.setText(noticiaModels.get(position).getDescripcion_publicacion());
     holder.lugarNoticia.setText(noticiaModels.get(position).getLugar());
     holder.fechaNoticia.setText(noticiaModels.get(position).getFecha_y_hora());
     holder.responsableNoticia.setText(noticiaModels.get(position).getResponsable());
     //holder.estadoNoticia.setText(noticiaModels.get(position).getEstado());
     //holder.tipoNoticia.setText(noticiaModels.get(position).getTipo());
        Glide.with(context).load(noticiaModels.get(position).getImagen()).into(holder.rutaarchivoNoticia);


    }

    @Override
    public int getItemCount() {
        return noticiaModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView rutaarchivoNoticia;
        private TextView nombreNoticia;
        private TextView descripcionNoticia;
        private TextView lugarNoticia;
        private TextView fechaNoticia;
        private TextView responsableNoticia;
      /*  private TextView estadoNoticia;
        private TextView tipoNoticia;*/

        public ViewHolder(View itemView) {
            super(itemView);
            rutaarchivoNoticia = itemView.findViewById(R.id.image_noticias);
            nombreNoticia = itemView.findViewById(R.id.tvNombre_noticia);
            descripcionNoticia = itemView.findViewById(R.id.tvDescripcion_noticia);
            lugarNoticia = itemView.findViewById(R.id.tvLugar_noticia);
            fechaNoticia= itemView.findViewById(R.id.tvFecha_noticia);
            responsableNoticia = itemView.findViewById(R.id.tvResponsable_noticia);
         /*   estadoNoticia = itemView.findViewById(R.id.tvEstado_noticia);
            tipoNoticia = itemView.findViewById(R.id.tvTipo_noticia);*/

        }
    }
}

package com.example.android_todes.models;

public class Evento_model {
    /* nombre_publicacion":" la rebeldia lesbica"
    ,"descripcion_publicacion":"El 13 de octubre se celebra.",
    "lugar":"parque caldas-popayan",
    "responsable":"Secretario de la comunidad ",
    "estado":"activo",
    "tipo":"evento",
    "image":"https:\/\/images.pexels.com\/photos\/1167034\/pexels-photo-1167034.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
    "fecha_y_hora":"2022-10-13 00:00:00"*/
    private String nombre_publicacion;
    private String descripcion_publicacion;
    private String lugar;
    private String responsable;
    private String estado;
    private String tipo;
    private String image;
    private String fecha_y_hora;

    public String getNombre_publicacion() {
        return nombre_publicacion;
    }

    public void setNombre_publicacion(String nombre_publicacion) {
        this.nombre_publicacion = nombre_publicacion;
    }

    public String getDescripcion_publicacion() {
        return descripcion_publicacion;
    }

    public void setDescripcion_publicacion(String descripcion_publicacion) {
        this.descripcion_publicacion = descripcion_publicacion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFecha_y_hora() {
        return fecha_y_hora;
    }

    public void setFecha_y_hora(String fecha_y_hora) {
        this.fecha_y_hora = fecha_y_hora;
    }
}

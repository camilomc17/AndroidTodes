package com.example.android_todes.models;

public class Noticia_model {
    /*"nombre_publicacion":"Muerte de un trans peruano",
    "descripcion_publicacion":"Rodrigo Ventocilla:
    la pol\u00e9micno tras ser detenidoolic\u00eda en Bali",
    "lugar":"Indonesia",
    "responsable":"Secretario de la comunidad ",
    "estado":"activo",
    "tipo":"noticia",
    "image":"https:\/\/images.pexels.com\/photos\/9587911\/pexels-photo-9587911.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load",
    "fecha_y_hora":"2022-11-25 20:22:11"*/
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

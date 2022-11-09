package com.example.android_todes.models;

public class Evento_model {
    private String nombre_publicacion;
    private String descripcion_publicacion;
    private String lugar;
    private String fecha_y_hora;
    private String responsable;
    private String estado;
    private String tipo;
    private String ruta_archivo;

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

    public String getFecha_y_hora() {
        return fecha_y_hora;
    }

    public void setFecha_y_hora(String fecha_y_hora) {
        this.fecha_y_hora = fecha_y_hora;
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

    public String getRuta_archivo() {
        return ruta_archivo;
    }

    public void setRuta_archivo(String ruta_archivo) {
        this.ruta_archivo = ruta_archivo;
    }
}

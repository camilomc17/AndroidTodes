package com.example.android_todes.models;

public class Incidencias_model {

    private String IdUsuario;
    private String estado;
    private String date;
    private String descripcion;
    private String barrio;
    private String edad;
    private String hora;
    private String nombres_apellidos;
    private String ubicacion;
    private String url_imagen;


    public Incidencias_model(String idUsuario, String estado, String date, String descripcion, String barrio, String edad, String hora, String nombres_apellidos, String ubicacion, String url_imagen) {
        IdUsuario = idUsuario;
        this.estado = estado;
        this.date = date;
        this.descripcion = descripcion;
        this.barrio = barrio;
        this.edad = edad;
        this.hora = hora;
        this.nombres_apellidos = nombres_apellidos;
        this.ubicacion = ubicacion;
        this.url_imagen = url_imagen;
    }


    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombres_apellidos() {
        return nombres_apellidos;
    }

    public void setNombres_apellidos(String nombres_apellidos) {
        this.nombres_apellidos = nombres_apellidos;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }
}

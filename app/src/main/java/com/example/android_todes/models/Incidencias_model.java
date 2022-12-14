package com.example.android_todes.models;

public class Incidencias_model {

   private String IdUsuario;
    private String barrio;
    private String date;
    private String descripcion;
    private String edad;
    private String estado;
    private String hora;
    private String nombres;
    private String ubicacion;
    private String urlimagen;

    public Incidencias_model(String idUsuario, String barrio, String date, String descripcion, String edad, String estado, String hora, String nombres, String ubicacion, String urlimagen) {
        this.IdUsuario = idUsuario;
        this.barrio = barrio;
        this.date = date;
        this.descripcion = descripcion;
        this.edad = edad;
        this.estado = estado;
        this.hora = hora;
        this.nombres = nombres;
        this.ubicacion = ubicacion;
        this.urlimagen = urlimagen;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }
}

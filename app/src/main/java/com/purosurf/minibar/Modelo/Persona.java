package com.purosurf.minibar.Modelo;

public class Persona {
    private int IdPersona;
    private String Nombre;
    private String Apellido;
    private String CorreoElectronico;
    private String PreguntaSeguridad;
    private String RespuestaSeguridad;

    public Persona(int idPersona, String nombre, String apellido, String correoElectronico, String preguntaSeguridad, String respuestaSeguridad) {
        IdPersona = idPersona;
        Nombre = nombre;
        Apellido = apellido;
        CorreoElectronico = correoElectronico;
        PreguntaSeguridad = preguntaSeguridad;
        RespuestaSeguridad = respuestaSeguridad;
    }

    public int getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(int idPersona) {
        IdPersona = idPersona;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        CorreoElectronico = correoElectronico;
    }

    public String getPreguntaSeguridad() {
        return PreguntaSeguridad;
    }

    public void setPreguntaSeguridad(String preguntaSeguridad) {
        PreguntaSeguridad = preguntaSeguridad;
    }

    public String getRespuestaSeguridad() {
        return RespuestaSeguridad;
    }

    public void setRespuestaSeguridad(String respuestaSeguridad) {
        RespuestaSeguridad = respuestaSeguridad;
    }
}

package com.purosurf.minibar.Modelo;

public class Usuario {
   private int IdUsuario;
   private String Usuario;
   private String Contrasena;
   private int IdEstado;
   private int IdPersona;

    public Usuario(int idUsuario, String usuario, String contrasena, int idEstado, int idPersona) {
        IdUsuario = idUsuario;
        Usuario = usuario;
        Contrasena = contrasena;
        IdEstado = idEstado;
        IdPersona = idPersona;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public int getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(int idEstado) {
        IdEstado = idEstado;
    }

    public int getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(int idPersona) {
        IdPersona = idPersona;
    }
}

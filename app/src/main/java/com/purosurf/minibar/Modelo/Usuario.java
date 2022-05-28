package com.purosurf.minibar.Modelo;

import android.text.TextUtils;

import com.purosurf.minibar.Modelo.Interfaces.IUsuario;

public class Usuario implements IUsuario {

   private int IdUsuario;
   private String Usuario;
   private String Contrasena;
   private int IdEstado;
   private int IdPersona;

    public Usuario(String usuario) {
        this.Usuario = usuario;
    }

    public Usuario(String usuario, String contrasena) {
        this.Usuario = usuario;
        this.Contrasena = contrasena;
    }

    public Usuario(int idUsuario, String usuario, String contrasena, int idEstado, int idPersona) {
        this.IdUsuario = idUsuario;
        this.Usuario = usuario;
        this.Contrasena = contrasena;
        this.IdEstado = idEstado;
        this.IdPersona = idPersona;
    }

    @Override
    public int getIdUsuario() {
        return IdUsuario;
    }

    @Override
    public void setIdUsuario(int idUsuario) {

    }

    @Override
    public String getUsuario() {
        return Usuario;
    }

    @Override
    public void setUsuario(String usuario) {

    }

    @Override
    public String getContrasena() {
        return Contrasena;
    }

    @Override
    public void setContrasena(String contrasena) {

    }

    @Override
    public int getIdEstado() {
        return IdEstado;
    }

    @Override
    public void setIdEstado(int idEstado) {

    }

    @Override
    public int getIdPersona() {
        return getIdPersona();
    }

    @Override
    public void setIdPersona(int idPersona) {

    }
}

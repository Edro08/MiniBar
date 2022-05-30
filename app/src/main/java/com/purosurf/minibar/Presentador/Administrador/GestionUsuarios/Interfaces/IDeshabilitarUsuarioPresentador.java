package com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Modelo.Usuario;

import java.util.List;

public interface IDeshabilitarUsuarioPresentador {
    List<Usuario> DatosUsuario(Context context, int idUsuario);
    void ActivarDesactivarUsuario(Context context, int idUsuario, boolean estado);
}

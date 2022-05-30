package com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.Interfaces;

import android.content.Context;
import android.database.Cursor;

public interface IConfirmarUsuarioPresentador {
    boolean VerificarUsuario(Context context, String usuario);
    int AdicionarPersona(Context context, String nombre, String apellido, String correo, String pregunta, String respuesta);
    void AdicionarUsuario(Context context, int idPersona, String usuario, String clave);
    void ActualizarUsuario(Context context, int idUsuario, String usuario, String clave);
    void ActualizarPersona(Context context, int idPersona, String nombre, String apellido, String correo, String pregunta, String respuesta);
    Cursor DatosUsuario(Context context, int idUsuario);
}

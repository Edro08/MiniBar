package com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.Interfaces;

import android.content.Context;
import android.database.Cursor;

import com.purosurf.minibar.Modelo.Persona;
import com.purosurf.minibar.Modelo.Usuario;

import java.util.List;

public interface IAdicionarActualizarUsuarioPresentador {
    boolean VerificarCorreoElectronico(Context context, String correo);
    Cursor DatosPersona(Context context, int idUsuario);

    /*
    boolean VerificarUsuario(String usuario);
    void AdicionarPersona(int idPersona, String nombre, String apellido, String corrreo, String pregunta, String respuesta);
    void ActualizarUsuario(int idUsuario, String usuario, String clave);
    void ActualizarPersona(int idPersona, String nombre, String apellido, String corrreo, String pregunta, String respuesta);
    List<Usuario> DatosUsuario(Context context, int idUsuario);

     */
}

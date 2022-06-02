package com.purosurf.minibar.Presentador.InicioSesion.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Modelo.Persona;

import java.util.List;

public interface IRecuperarCuentaPresentador {
    int NumeroAleatorio();
    String PreguntaPersona(Context context, int iSdUsuario);
    boolean VerificarCorreo(Context context, String correo, int idUsuario);
    boolean VerificarRespuesta(Context context, String respuesta, int idUsuario);
    boolean EnviarCorreo(String correo, int codigo);
}

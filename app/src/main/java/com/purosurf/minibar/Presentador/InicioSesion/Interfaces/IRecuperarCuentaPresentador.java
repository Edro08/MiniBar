package com.purosurf.minibar.Presentador.InicioSesion.Interfaces;

import android.content.Context;

public interface IRecuperarCuentaPresentador {
    int NumeroAleatorio();
    String VerificarCorreo(Context context, String correo, int idUsuario);
    String VerificarRespuesta(Context context, String respuesta, int idUsuario);
    boolean EnviarCorreo(String correo, int codigo);
}

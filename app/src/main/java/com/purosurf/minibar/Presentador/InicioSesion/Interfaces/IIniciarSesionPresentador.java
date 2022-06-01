package com.purosurf.minibar.Presentador.InicioSesion.Interfaces;

import android.content.Context;

public interface IIniciarSesionPresentador {
    boolean OnIniciarSesion(String Usuario, String Contrasena, Context context);
    boolean typeUser(String Usuario);
    boolean VerificarUsuario(Context context, String usuario);
}

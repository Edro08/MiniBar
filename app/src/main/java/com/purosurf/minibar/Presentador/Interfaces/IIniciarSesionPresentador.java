package com.purosurf.minibar.Presentador.Interfaces;

import android.content.Context;

public interface IIniciarSesionPresentador {
    boolean OnIniciarSesion(String Usuario, String Contrasena, Context context);
    boolean typeUser(String Usuario);
}

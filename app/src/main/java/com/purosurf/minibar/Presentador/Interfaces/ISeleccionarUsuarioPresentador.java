package com.purosurf.minibar.Presentador.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Modelo.Usuario;

import java.util.List;
import java.util.Objects;

public interface ISeleccionarUsuarioPresentador {
    List <Usuario> listaUsuarios(Context context);
}

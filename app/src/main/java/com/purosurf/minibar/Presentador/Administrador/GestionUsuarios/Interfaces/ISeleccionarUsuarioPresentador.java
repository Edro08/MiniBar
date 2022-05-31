package com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Modelo.Usuario;

import java.util.List;

public interface ISeleccionarUsuarioPresentador {
    List <Usuario> listaUsuarios(Context context,String accion);
}

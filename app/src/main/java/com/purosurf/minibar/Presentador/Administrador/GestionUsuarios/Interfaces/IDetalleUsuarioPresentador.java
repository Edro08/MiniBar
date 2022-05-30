package com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.Interfaces;

import android.content.Context;
import android.database.Cursor;

import com.purosurf.minibar.Modelo.Usuario;

import java.util.List;

public interface IDetalleUsuarioPresentador {
    Cursor DatosPersonaUsuario(Context context, int idUsuario);
}

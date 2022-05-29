package com.purosurf.minibar.Presentador.Administrador.GestionProductos.Interfaces;

import android.content.Context;
import android.database.Cursor;

import com.purosurf.minibar.Modelo.Categoria;
import com.purosurf.minibar.Modelo.Producto;

import java.util.List;

public interface ISeleccionarProductoPresentador {
    Cursor CursorProductos(Context context, String nombreCategoria);
    List<String> listaCategorias(Context context);
}

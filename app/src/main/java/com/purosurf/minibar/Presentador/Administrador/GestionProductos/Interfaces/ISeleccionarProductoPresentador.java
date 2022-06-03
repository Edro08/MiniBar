package com.purosurf.minibar.Presentador.Administrador.GestionProductos.Interfaces;

import android.content.Context;
import android.database.Cursor;

import com.purosurf.minibar.Modelo.Categoria;
import com.purosurf.minibar.Modelo.Inventario;
import com.purosurf.minibar.Modelo.Producto;

import java.util.List;

public interface ISeleccionarProductoPresentador {
    List<Producto> listaProductos(Context context, String nombreCategoria);
    List<String> listaCategorias(Context context);
    Boolean InsertarProd_Ivnt(Producto producto, Context context, Inventario inventario);
}

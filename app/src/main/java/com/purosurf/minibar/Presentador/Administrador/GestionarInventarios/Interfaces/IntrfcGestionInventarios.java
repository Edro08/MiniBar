package com.purosurf.minibar.Presentador.Administrador.GestionarInventarios.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Modelo.Inventario;
import com.purosurf.minibar.Modelo.Producto;

import java.util.List;

public interface IntrfcGestionInventarios {

    List<Inventario> listaProductos(Context context, String nombreCategoria);
    List<String> listaCategorias(Context context);
    Boolean ExisteReg(int idInventario);
}

package com.purosurf.minibar.Presentador.Administrador.GestionProductos.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Modelo.Producto;

import java.util.ArrayList;

public interface IActualizarProductoPresentador {
    void obtenerProducto(Context context, int idproducto);
    boolean verificarProducto(Context context, String nombreProducto);
    ArrayList<Integer> Cantidades(Context context);
    void actualizarPoducto(Context context, int idproducto,String nombre, String categoria, float precio, boolean estado, int minima, int maxima, String url);

}

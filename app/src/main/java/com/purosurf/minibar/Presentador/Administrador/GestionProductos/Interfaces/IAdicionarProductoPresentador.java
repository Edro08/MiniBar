package com.purosurf.minibar.Presentador.Administrador.GestionProductos.Interfaces;

import android.content.Context;

public interface IAdicionarProductoPresentador {
    void AdicionarProducto(Context context, String nombre, String categoria, float precio, boolean estado, int minima, int maxima, String url);
    boolean verificarProducto(Context context, String nombreProducto);
}

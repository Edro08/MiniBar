package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces;

import android.content.Context;

public interface IAgregarProductoMBPresentador {
    boolean VerificarInventarioHabitacion(Context context, int idProducto, int idHabitacion);
    boolean AgregarEliminarProductoMiniBar(Context context, int idProducto, int idHabitacion,
                                           String nombreHabitacion, boolean estadoRegistro, int existencias,
                                           int existenciasActual,int cantidadMin, double precio, int idUsuario);
}

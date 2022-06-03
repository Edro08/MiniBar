package com.purosurf.minibar.Presentador.Empleado.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Modelo.InventarioHabitacionProducto;

import java.util.List;

public interface IRegistrarConsumosPresentador {
    List<InventarioHabitacionProducto> listaInventarioHabitacion(Context context, int IdHabitacion);
    double CalcularTotal(List<InventarioHabitacionProducto> lista);
    int getCantidadRegistros(List<InventarioHabitacionProducto> lista);
    String[][] obtenerDatosDetalleProductos(List<InventarioHabitacionProducto> lista, int Cantidad);
}

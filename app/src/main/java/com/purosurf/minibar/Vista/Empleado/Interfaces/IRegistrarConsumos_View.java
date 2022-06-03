package com.purosurf.minibar.Vista.Empleado.Interfaces;

import com.purosurf.minibar.Modelo.InventarioHabitacionProducto;

import java.util.List;

public interface IRegistrarConsumos_View {
    void MostrarTotal(List<InventarioHabitacionProducto> lista);
    void ExistenciaMaxima(String msg);
    void CalcularCantidadDetalleProductos(List<InventarioHabitacionProducto> lista);
}

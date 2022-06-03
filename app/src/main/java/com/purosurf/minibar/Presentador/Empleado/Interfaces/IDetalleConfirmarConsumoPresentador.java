package com.purosurf.minibar.Presentador.Empleado.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Vista.Empleado.RegistrarConsumos;

public interface IDetalleConfirmarConsumoPresentador {

    boolean RegistrarConsumos(Context context, int IdUsuario, int IdHabitacion, double total);
    boolean RegistrarDetalleConsumo(Context context, String[][] detalleConsumo, int IdConsumo, int IdHabitacion);
}

package com.purosurf.minibar.Presentador.AdministradorEmpleado.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Modelo.Habitacion;

import java.util.List;

public interface ISeleccionarReporteConsumo {
    List<Habitacion> DatosHabitacion(Context context);
}

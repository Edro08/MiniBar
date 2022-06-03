package com.purosurf.minibar.Presentador.AdministradorEmpleado.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Modelo.Consumo;
import com.purosurf.minibar.Modelo.Habitacion;

import java.util.List;

public interface ISeleccionarReporteConsumo {
    List<Habitacion> DatosHabitacion(Context context);
    List<Consumo> DatosConsumoHabitacion(Context context, int IdHabitacion, String FechaDesde, String FechaHasta);
}

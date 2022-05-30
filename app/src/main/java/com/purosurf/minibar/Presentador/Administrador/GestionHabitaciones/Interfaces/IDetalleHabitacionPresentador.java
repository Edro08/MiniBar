package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Modelo.Habitacion;

import java.util.List;

public interface IDetalleHabitacionPresentador {
    List<Habitacion> datosHabitacion(Context context, int idHabitacion);
}

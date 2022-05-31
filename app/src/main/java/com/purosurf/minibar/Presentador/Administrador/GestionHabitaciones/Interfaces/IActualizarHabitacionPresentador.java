package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Modelo.Habitacion;

import java.util.List;

public interface IActualizarHabitacionPresentador {
    void ActualizarHabitacion(Context context,int idHabitacion, String nombreHabitacion);
    boolean verificarHabitacion(Context context, String nombreHabitacion);
    List<Habitacion> DatosHabitacion(Context context, int idHabitacion);
}

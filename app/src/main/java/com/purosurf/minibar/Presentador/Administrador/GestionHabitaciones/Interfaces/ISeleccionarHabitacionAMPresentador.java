package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Modelo.Habitacion;

import java.util.List;

public interface ISeleccionarHabitacionAMPresentador {
    List<Habitacion> listaHabitacion(Context context, String accion);
}

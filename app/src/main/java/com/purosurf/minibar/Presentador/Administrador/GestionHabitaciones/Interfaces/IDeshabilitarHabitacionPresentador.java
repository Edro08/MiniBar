package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces;

import android.content.Context;
import android.database.Cursor;

import com.purosurf.minibar.Modelo.Habitacion;

import java.util.List;

public interface IDeshabilitarHabitacionPresentador {

    List<Habitacion> DatosHabitacion(Context context, int idHabitacion);
    void ActivarDesactivarHabitacion(Context context, int idHabitacion, boolean estado);
}

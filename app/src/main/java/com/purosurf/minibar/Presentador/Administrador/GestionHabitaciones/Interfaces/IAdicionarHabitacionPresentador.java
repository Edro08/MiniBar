package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces;

import android.content.Context;

public interface IAdicionarHabitacionPresentador {
    void AdicionarHabitacion(Context context, String nombreHabitacion, boolean estado);
    boolean verificarHabitacion(Context context, String nombreHabitacion);
}

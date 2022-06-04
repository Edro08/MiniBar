package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces;

import android.content.Context;

import com.purosurf.minibar.Modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public interface IInventarioMBPresentador {
    ArrayList<String> FiltoProductos();
    List<Producto> listaProductos(Context context, String filto, int idHabitacion);
}

package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces.IDetalleHabitacionPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.IDetalleHabitacion_View;

import java.util.ArrayList;
import java.util.List;

public class DetalleHabitacionPresentador implements IDetalleHabitacionPresentador {

    IDetalleHabitacion_View iDetalleHabitacion_view;

    public DetalleHabitacionPresentador (IDetalleHabitacion_View iDetalleHabitacion_view)
    {
        this.iDetalleHabitacion_view = iDetalleHabitacion_view;
    }

    @Override
    public List<Habitacion> datosHabitacion(Context context, int idHabitacion) {
        //llenar lista
        List<Habitacion> listaHabitacion = new ArrayList<>();
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT * FROM HABITACION WHERE IDHABITACION = '" + idHabitacion +"'";
        Cursor datos = base.rawQuery(consultaSql, null);
        while(datos.moveToNext()){
            listaHabitacion.add(new Habitacion(datos.getInt(0), datos.getString(1), datos.getInt(2)));
        }
        datos.close();
        return listaHabitacion;
    }
}

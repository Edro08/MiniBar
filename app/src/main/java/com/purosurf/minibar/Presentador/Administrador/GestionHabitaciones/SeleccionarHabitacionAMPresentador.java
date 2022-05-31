package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces.ISeleccionarHabitacionAMPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.ISeleccionarHabitacionAM_View;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarHabitacionAMPresentador implements ISeleccionarHabitacionAMPresentador {

    ISeleccionarHabitacionAM_View iSeleccionarHabitacionAM_view;

    public SeleccionarHabitacionAMPresentador (ISeleccionarHabitacionAM_View iSeleccionarHabitacionAM_view)
    {
        this.iSeleccionarHabitacionAM_view = iSeleccionarHabitacionAM_view;
    }

    @Override
    public List<Habitacion> listaHabitacion(Context context, String accion) {
        //llenar lista
        List<Habitacion> listaHabitacion = new ArrayList<>();
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        if(accion.equals("deshabilitar"))
        {
            consultaSql = "SELECT * FROM HABITACION";
        }else
        {
            consultaSql = "SELECT * FROM HABITACION WHERE IDESTADO = 1";
        }
        Cursor datos = base.rawQuery(consultaSql, null);
        while(datos.moveToNext()){
            listaHabitacion.add(new Habitacion(datos.getInt(0), datos.getString(1), datos.getInt(2)));
        }
        datos.close();
        return listaHabitacion;
    }
}

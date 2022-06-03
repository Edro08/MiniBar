package com.purosurf.minibar.Presentador.Empleado;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.Empleado.Interfaces.ISeleccionarHabitacionEMPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.ISeleccionarHabitacionAM_View;
import com.purosurf.minibar.Vista.Empleado.Interfaces.ISeleccionarHabitacionEM_View;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarHabitacionEMPresentador implements ISeleccionarHabitacionEMPresentador {

    ISeleccionarHabitacionEM_View iSeleccionarHabitacionEM_view;

    public SeleccionarHabitacionEMPresentador ( ISeleccionarHabitacionEM_View iSeleccionarHabitacionEM_view)
    {
        this.iSeleccionarHabitacionEM_view = iSeleccionarHabitacionEM_view;
    }

    @Override
    public List<Habitacion> listaHabitacion(Context context) {
        //llenar lista
        List<Habitacion> listaHabitacion = new ArrayList<>();
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT * FROM HABITACION WHERE IDESTADO = 1";
        Cursor datos = base.rawQuery(consultaSql, null);
        while(datos.moveToNext()){
            listaHabitacion.add(new Habitacion(datos.getInt(0), datos.getString(1), datos.getInt(2)));
        }
        datos.close();
        return listaHabitacion;
    }
}

package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces.IDeshabilitarHabitacionPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.IDeshabilitarHabitacion_View;

import java.util.ArrayList;
import java.util.List;

public class DeshabilitarHabitacionPresentador implements IDeshabilitarHabitacionPresentador {

    IDeshabilitarHabitacion_View iDeshabilitarHabitacion_view;

    public DeshabilitarHabitacionPresentador(IDeshabilitarHabitacion_View iDeshabilitarHabitacion_view)
    {
        this.iDeshabilitarHabitacion_view = iDeshabilitarHabitacion_view;
    }

    @Override
    public List<Habitacion> DatosHabitacion(Context context, int idHabitacion) {
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

    @Override
    public void ActivarDesactivarHabitacion(Context context, int idHabitacion, boolean estado) {
        int idEstado;
        if(estado)
            idEstado = 1;
        else
            idEstado = 2;

        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "UPDATE HABITACION SET IDESTADO = '" + idEstado + "' WHERE IDHABITACION = '" + idHabitacion + "'";
        base.execSQL(consultaSql);
    }
}

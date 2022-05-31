package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces.IActualizarHabitacionPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.IActualizarHabitacion_View;

import java.util.ArrayList;
import java.util.List;

public class ActualizarHabitacionPresentador implements IActualizarHabitacionPresentador {

    IActualizarHabitacion_View iActualizarHabitacion_view;

    public ActualizarHabitacionPresentador(IActualizarHabitacion_View iActualizarHabitacion_view)
    {
        this.iActualizarHabitacion_view = iActualizarHabitacion_view;
    }

    @Override
    public void ActualizarHabitacion(Context context,int idHabitacion ,String nombreHabitacion) {
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "UPDATE HABITACION SET " +
                "NOMBREHABITACION = '"+ nombreHabitacion +"'" +
                "WHERE IDHABITACION = '"+ idHabitacion +"'";
        base.execSQL(consultaSql);
    }

    @Override
    public boolean verificarHabitacion(Context context, String nombreHabitacion) {
        boolean estado = false;
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT * FROM HABITACION " +
                "WHERE NOMBREHABITACION = '" + nombreHabitacion + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        if(datos.moveToFirst() == true)
        {
            estado = true;
        }
        return estado;
    }

    @Override
    public List<Habitacion> DatosHabitacion(Context context, int idHabitacion) {
        //Declarar lista
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

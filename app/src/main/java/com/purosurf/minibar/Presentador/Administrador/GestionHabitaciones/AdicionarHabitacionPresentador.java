package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces.IAdicionarHabitacionPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.IAdicionarHabitacion_View;

public class AdicionarHabitacionPresentador implements IAdicionarHabitacionPresentador {

    IAdicionarHabitacion_View iAdicionarHabitacion_view;

    public AdicionarHabitacionPresentador(IAdicionarHabitacion_View iAdicionarHabitacion_view)
    {
        this.iAdicionarHabitacion_view = iAdicionarHabitacion_view;
    }

    @Override
    public void AdicionarHabitacion(Context context, String nombreHabitacion, boolean estado) {
        int idEstado;
        if(estado)
            idEstado = 1;
        else
            idEstado = 2;

        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "INSERT INTO HABITACION(NOMBREHABITACION,IDESTADO) " +
                "VALUES('"+ nombreHabitacion +"','"+ idEstado +"')";
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
}

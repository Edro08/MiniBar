package com.purosurf.minibar.Presentador.AdministradorEmpleado;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Consumo;
import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.AdministradorEmpleado.Interfaces.ISeleccionarReporteConsumo;
import com.purosurf.minibar.Vista.AdministradorEmpleado.Interfaces.ISeleccionarReporteConsumo_View;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarReporteConsumoPresentador implements ISeleccionarReporteConsumo {
    ISeleccionarReporteConsumo_View iSeleccionarReporteCompras_view;

    public SeleccionarReporteConsumoPresentador(ISeleccionarReporteConsumo_View iSeleccionarReporteCompras_view)
    {
        this.iSeleccionarReporteCompras_view = iSeleccionarReporteCompras_view;
    }

    @Override
    public List<Habitacion> DatosHabitacion(Context context) {
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
            iSeleccionarReporteCompras_view.ObtenerNombreHabitacion(datos.getString(1));
        }
        datos.close();
        return listaHabitacion;
    }

    @Override
    public List<Consumo> DatosConsumoHabitacion(Context context, int IdHabitacion,
                                                String FechaDesde, String FechaHasta) {
        //llenar lista
        List<Consumo> datosConsumoHabitacion = new ArrayList<>();
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT * FROM CONSUMO " +
                "WHERE IDHABITACION = '" + IdHabitacion + "'" +
                "AND FECHA BETWEEN '" + FechaDesde + "' AND '" + FechaHasta + "'" +
                "ORDER BY FECHA DESC";
        Cursor datos = base.rawQuery(consultaSql, null);
        while(datos.moveToNext()){
            datosConsumoHabitacion.add(new Consumo(datos.getInt(0),datos.getInt(1),
                    datos.getInt(2), datos.getString(3), datos.getFloat(4)));
            Log.e("Tag","No hay datos");
        }
        datos.close();
        return datosConsumoHabitacion;
    }
}

package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces.IAgregarProductoMBPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.IAgregarProductoMB_View;

public class AgregarProductoMBPresentador implements IAgregarProductoMBPresentador {

    IAgregarProductoMB_View iAgregarProductoMB_view;

    public AgregarProductoMBPresentador(IAgregarProductoMB_View iAgregarProductoMB_view)
    {
        this.iAgregarProductoMB_view = iAgregarProductoMB_view;
    }


    @Override
    public boolean VerificarInventarioHabitacion(Context context, int idProducto, int idHabitacion) {
        boolean estado = false;
        Log.e("Fase 1", "Todo");
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT * FROM INVENTARIOHABITACION WHERE IDHABITACION = '" + idHabitacion + "' " +
                "AND IDPRODUCTO = '" + idProducto + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        if(datos.moveToFirst())
        {
            iAgregarProductoMB_view.ObtenerExistenciasActuales(datos.getInt(3));
            iAgregarProductoMB_view.ObtenerCantidadMinActual(datos.getInt(4));
            estado = true;
        }

        datos.close();
        return estado;
    }
}


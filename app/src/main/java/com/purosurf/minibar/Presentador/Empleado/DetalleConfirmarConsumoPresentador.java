package com.purosurf.minibar.Presentador.Empleado;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Presentador.Empleado.Interfaces.IDetalleConfirmarConsumoPresentador;
import com.purosurf.minibar.Vista.Empleado.Interfaces.IDetalleConfirmarConsumo_View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetalleConfirmarConsumoPresentador implements IDetalleConfirmarConsumoPresentador {

    IDetalleConfirmarConsumo_View iDetalleConfirmarConsumo_view;

    public DetalleConfirmarConsumoPresentador(IDetalleConfirmarConsumo_View iDetalleConfirmarConsumo_view)
    {
        this.iDetalleConfirmarConsumo_view = iDetalleConfirmarConsumo_view;
    }

    @Override
    public boolean RegistrarConsumos(Context context, int IdUsuario, int IdHabitacion, double total) {
        boolean estado = false;
        Cursor idConsumo;
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "INSERT INTO CONSUMO(IDUSUARIO,IDHABITACION,FECHA,TOTAL) " +
                "VALUES('"+ IdUsuario +"', '"+ IdHabitacion +"','"+ fecha +"'," +
                "'"+ total +"')";
        base.execSQL(consultaSql);
        idConsumo = base.rawQuery("SELECT last_insert_rowid() FROM CONSUMO", null);
        if(idConsumo.moveToFirst())
        {
            iDetalleConfirmarConsumo_view.ObtenerIdConsumo(idConsumo.getInt(0));
            estado = true;
        }
        return estado;
    }

    @Override
    public boolean RegistrarDetalleConsumo(Context context, String[][] detalleConsumo,
                                           int IdConsumo, int IdHabitacion) {
        boolean estado = false;
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        for (int i = 0; i < detalleConsumo.length; i++)
        {
            consultaSql = "INSERT INTO DETALLECONSUMO(IDCONSUMO,IDPRODUCTO,CANTIDAD,SUBTOTAL) " +
                    "VALUES('"+ IdConsumo +"', '"+ detalleConsumo[i][0] +"','"+ detalleConsumo[i][2] +"'," +
                    "'"+ detalleConsumo[i][3] +"')";
            base.execSQL(consultaSql);
            consultaSql = "UPDATE INVENTARIOHABITACION " +
                    "SET EXISTENCIAS = EXISTENCIAS - "+ detalleConsumo[i][2] + " " +
                    "WHERE IDPRODUCTO = '"+ detalleConsumo[i][0] +"'" +
                    "AND IDHABITACION = '"+ IdHabitacion +"'";
            base.execSQL(consultaSql);
            estado = true;
        }

        return estado;
    }
}

package com.purosurf.minibar.Presentador.AdministradorEmpleado;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Presentador.AdministradorEmpleado.Interfaces.IDetalleReporteConsPresentador;
import com.purosurf.minibar.Vista.AdministradorEmpleado.Interfaces.IDetalleReporteCons_View;

public class DetalleReporteConsPresentador implements IDetalleReporteConsPresentador {
    IDetalleReporteCons_View iDetalleReporteCons_view;

    public DetalleReporteConsPresentador(IDetalleReporteCons_View iDetalleReporteCons_view)
    {
        this.iDetalleReporteCons_view = iDetalleReporteCons_view;
    }


    @Override
    public Cursor DatosConsumoHabitacion(Context context, int IdConsumo) {
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT PRODUCTO.IDPRODUCTO, PRODUCTO.PRODUCTONOMBRE, " +
                "DETALLECONSUMO.CANTIDAD, DETALLECONSUMO.SUBTOTAL " +
                "FROM DETALLECONSUMO INNER JOIN PRODUCTO ON DETALLECONSUMO.IDPRODUCTO = PRODUCTO.IDPRODUCTO " +
                "INNER JOIN CONSUMO ON DETALLECONSUMO.IDCONSUMO = CONSUMO.IDCONSUMO " +
                "WHERE CONSUMO.IDCONSUMO = '" + IdConsumo + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        return datos;
    }

    @Override
    public Cursor DatosInventario(Context context) {
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT PRODUCTO.IDPRODUCTO, PRODUCTONOMBRE,NOMBRECATEGORIA, EXISTENCIAS, " +
                "PRECIOUNITARIO, ESTADONOMBRE " +
                "FROM PRODUCTO INNER JOIN INVENTARIO ON INVENTARIO.IDPRODUCTO = PRODUCTO.IDPRODUCTO " +
                "INNER JOIN CATEGORIA ON PRODUCTO.IDCATEGORIA = CATEGORIA.IDCATEGORIA " +
                "INNER JOIN ESTADO ON PRODUCTO.IDESTADO = ESTADO.IDESTADO";
        Cursor datos = base.rawQuery(consultaSql, null);
        return datos;
    }

    @Override
    public Cursor DatosCompra(Context context, String FechaDesde, String FechaHasta) {
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT IDENTRADA, USUARIO, PRODUCTO.IDPRODUCTO, PRODUCTONOMBRE, FECHA, CANTIDAD, PRECIO, TOTAL " +
                "FROM ENTRADA INNER JOIN PRODUCTO ON ENTRADA.IDPRODUCTO = PRODUCTO.IDPRODUCTO " +
                "INNER JOIN USUARIO ON ENTRADA.IDUSUARIO = USUARIO.IDUSUARIO " +
                "WHERE FECHA BETWEEN '" + FechaDesde + "' AND '" + FechaHasta + "' " +
                "ORDER BY IDENTRADA DESC";
        Cursor datos = base.rawQuery(consultaSql, null);
        return datos;
    }
}

package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces.IAgregarProductoMBPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.IAgregarProductoMB_View;
import com.purosurf.minibar.Vista.AdministradorEmpleado.VerExistencias;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AgregarProductoMBPresentador implements IAgregarProductoMBPresentador {

    IAgregarProductoMB_View iAgregarProductoMB_view;

    public AgregarProductoMBPresentador(IAgregarProductoMB_View iAgregarProductoMB_view)
    {
        this.iAgregarProductoMB_view = iAgregarProductoMB_view;
    }


    @Override
    public boolean VerificarInventarioHabitacion(Context context, int idProducto, int idHabitacion) {
        boolean estado = false;
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

        consultaSql = "SELECT EXISTENCIAS FROM INVENTARIO " +
                "WHERE IDPRODUCTO = '" + idProducto + "' ";
        datos = base.rawQuery(consultaSql, null);
        if(datos.moveToFirst())
        {
            iAgregarProductoMB_view.ObtenerExistenciasInventarioGeneral(datos.getInt(0));
        }
        datos.close();
        return estado;
    }

    @Override
    public boolean AgregarEliminarProductoMiniBar(Context context, int idProducto, int idHabitacion,
                                                  String nombreHabitacion, boolean estadoRegistro, int existencias,
                                                  int existenciasActual,int cantidadMin, double precio, int idUsuario)
    {
        boolean estado = false;
        String Descripcion = "", fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int cantidad = 0;
        double total = 0;

        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;

        if(!estadoRegistro)
        {
            consultaSql = "INSERT INTO INVENTARIOHABITACION(IDHABITACION,IDPRODUCTO,EXISTENCIAS,CANTIDADMINIMA) " +
                    "VALUES('" + idHabitacion + "','" + idProducto + "',0,0)";
            base.execSQL(consultaSql);
        }

        consultaSql = "SELECT IDINVENTARIOHABITACION FROM INVENTARIOHABITACION " +
                "WHERE IDHABITACION = '" + idHabitacion + "' " +
                "AND IDPRODUCTO = '" + idProducto + "'";
        Cursor datos = base.rawQuery(consultaSql, null);

        if(datos.moveToFirst())
        {
            if(existenciasActual < existencias)
            {
                Descripcion = "Entrada de producto al Minibar de la habitacion: " + nombreHabitacion;
                cantidad = existencias - existenciasActual;
                total = precio * cantidad;

                consultaSql = "INSERT INTO SALIDA(IDUSUARIO,IDPRODUCTO,DESCRIPCION,FECHA,CANTIDAD,PRECIO,TOTAL) " +
                        "VALUES('" + idUsuario + "','" + idProducto + "','" + Descripcion + "','" + fecha + "'," +
                        "'" + cantidad + "', '" + precio + "', '" + total + "')";
                base.execSQL(consultaSql);

                consultaSql = "UPDATE INVENTARIO " +
                        "SET EXISTENCIAS = EXISTENCIAS - '" + cantidad + "' " +
                        "WHERE IDPRODUCTO = '" + idProducto + "'";
                base.execSQL(consultaSql);
            }
            else if(existenciasActual > existencias)
            {
                cantidad = existenciasActual - existencias;
                total = precio * cantidad;

                Descripcion = "Retirada de producto de Minibar de la habitacion: " + nombreHabitacion;
                consultaSql = "INSERT INTO ENTRADA(IDUSUARIO,IDPRODUCTO,DESCRIPCION,FECHA,CANTIDAD,PRECIO,TOTAL) " +
                        "VALUES('" + idUsuario + "','" + idProducto + "','" + Descripcion + "','" + fecha + "'," +
                        "'" + cantidad + "', '" + precio + "', '" + total + "')";
                base.execSQL(consultaSql);

                consultaSql = "UPDATE INVENTARIO " +
                        "SET EXISTENCIAS = EXISTENCIAS + '" + cantidad + "' " +
                        "WHERE IDPRODUCTO = '" + idProducto + "'";
                base.execSQL(consultaSql);
            }

            consultaSql = "UPDATE INVENTARIOHABITACION " +
                    "SET EXISTENCIAS = '" + existencias + "', " +
                    "CANTIDADMINIMA = '" + cantidadMin + "'" +
                    "WHERE IDINVENTARIOHABITACION = '" + datos.getString(0) + "'";
            base.execSQL(consultaSql);
            estado = true;
        }

        return estado;
    }
}


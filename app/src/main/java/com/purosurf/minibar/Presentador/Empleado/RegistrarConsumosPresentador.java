package com.purosurf.minibar.Presentador.Empleado;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;

import com.purosurf.minibar.Modelo.InventarioHabitacionProducto;
import com.purosurf.minibar.Presentador.Empleado.Interfaces.IRegistrarConsumosPresentador;
import com.purosurf.minibar.Vista.Empleado.Interfaces.IRegistrarConsumos_View;

import java.util.ArrayList;
import java.util.List;

public class RegistrarConsumosPresentador implements IRegistrarConsumosPresentador {
    IRegistrarConsumos_View iRegistrarConsumos_view;

    public RegistrarConsumosPresentador  ( IRegistrarConsumos_View iRegistrarConsumos_view)
    {
        this.iRegistrarConsumos_view = iRegistrarConsumos_view;
    }

    @Override
    public List<InventarioHabitacionProducto> listaInventarioHabitacion(Context context, int idHabitacion) {
        //llenar lista
        List<InventarioHabitacionProducto> listaInventarioHabitacion = new ArrayList<>();
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT INVENTARIOHABITACION.IDINVENTARIOHABITACION, HABITACION.IDHABITACION," +
                "HABITACION.NOMBREHABITACION, PRODUCTO.IDPRODUCTO, PRODUCTO.PRODUCTONOMBRE," +
                "PRODUCTO.PRECIOUNITARIO, INVENTARIOHABITACION.EXISTENCIAS ,INVENTARIOHABITACION.CANTIDADMINIMA FROM INVENTARIOHABITACION " +
                "INNER JOIN HABITACION ON HABITACION.IDHABITACION = INVENTARIOHABITACION.IDHABITACION " +
                "INNER JOIN PRODUCTO ON PRODUCTO.IDPRODUCTO = INVENTARIOHABITACION.IDPRODUCTO " +
                "WHERE INVENTARIOHABITACION.IDHABITACION = '"+ idHabitacion +"'";
        Cursor datos = base.rawQuery(consultaSql, null);
        while(datos.moveToNext()){
            listaInventarioHabitacion.add(new InventarioHabitacionProducto(datos.getInt(0),datos.getInt(1),
                    datos.getString(2),datos.getInt(3),datos.getString(4), datos.getFloat(5),datos.getInt(6),
                    datos.getInt(7),0,0));
        }
        datos.close();
        return listaInventarioHabitacion;
    }

    @Override
    public double CalcularTotal(List<InventarioHabitacionProducto> lista) {
        double total = 0;
        for (int i = 0; i < lista.size(); i++)
        {
            total = total + lista.get(i).getSubTotal();
        }
        return total;
    }

    @Override
    public int getCantidadRegistros(List<InventarioHabitacionProducto> lista) {
        int Cantidad = 0;

        for (int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).getCantidad() > 0)
            {
                Cantidad++;
            }
        }
        return Cantidad;
    }

    @Override
    public String[][] obtenerDatosDetalleProductos(List<InventarioHabitacionProducto> lista, int Cantidad) {
        String[][] Datos = new String[Cantidad][4];
        for (int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).getCantidad() > 0)
            {
                Datos[i][0] = String.valueOf(lista.get(i).getIdProducto());
                Datos[i][1] = String.valueOf(lista.get(i).getProductoNombre());
                Datos[i][2] = String.valueOf(lista.get(i).getCantidad());
                Datos[i][3] = String.valueOf(lista.get(i).getSubTotal());
            }
        }
        return Datos;
    }
}

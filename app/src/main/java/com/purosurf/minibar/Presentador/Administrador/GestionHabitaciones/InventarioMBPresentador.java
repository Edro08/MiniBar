package com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.InventarioHabitacionProducto;
import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.Interfaces.IInventarioMBPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.IInventarioMB_View;

import java.util.ArrayList;
import java.util.List;

public class InventarioMBPresentador implements IInventarioMBPresentador {

    IInventarioMB_View iInventarioMB_view;

    public InventarioMBPresentador(IInventarioMB_View iInventarioMB_view)
    {
        this.iInventarioMB_view = iInventarioMB_view;
    }


    @Override
    public ArrayList<String> FiltoProductos() {
        ArrayList<String> filtro = new ArrayList<>();
        filtro.add("Todos Los Productos");
        filtro.add("Producto En Existencias");
        filtro.add("Producto Sin Existencias");
        return filtro;
    }

    @Override
    public List<Producto> listaProductos(Context context, String filto, int idHabitacion) {

        //llenar lista
        List<Producto> listaProducto = new ArrayList<>();
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql = "";
        if(filto.equals("Producto En Existencias"))
        {
            consultaSql = "SELECT PRODUCTO.IDPRODUCTO, PRODUCTO.PRODUCTONOMBRE, PRODUCTO.IDCATEGORIA," +
                    "PRODUCTO.PRECIOUNITARIO, PRODUCTO.IDESTADO, PRODUCTO.IMAGENURL " +
                    "FROM PRODUCTO INNER JOIN INVENTARIOHABITACION " +
                    "ON PRODUCTO.IDPRODUCTO = INVENTARIOHABITACION.IDPRODUCTO " +
                    "WHERE INVENTARIOHABITACION.IDHABITACION = '"+ idHabitacion +"'" +
                    "AND INVENTARIOHABITACION.EXISTENCIAS > 0";
        }else if(filto.equals("Producto Sin Existencias"))
        {
            consultaSql = "SELECT PRODUCTO.IDPRODUCTO, PRODUCTO.PRODUCTONOMBRE, PRODUCTO.IDCATEGORIA," +
                    "PRODUCTO.PRECIOUNITARIO, PRODUCTO.IDESTADO, PRODUCTO.IMAGENURL " +
                    "FROM PRODUCTO INNER JOIN INVENTARIOHABITACION " +
                    "ON PRODUCTO.IDPRODUCTO = INVENTARIOHABITACION.IDPRODUCTO " +
                    "WHERE INVENTARIOHABITACION.IDHABITACION = '"+ idHabitacion +"'" +
                    "AND INVENTARIOHABITACION.EXISTENCIAS = 0 AND PRODUCTO.IDESTADO = 1";
        }else if(filto.equals("Todos Los Productos"))
        {
            consultaSql = "SELECT * FROM  PRODUCTO " +
                    "WHERE PRODUCTO.IDESTADO = 1";
        }

        Cursor datos = base.rawQuery(consultaSql, null);
        while(datos.moveToNext()){
            listaProducto.add(new Producto(datos.getInt(0),datos.getString(1),datos.getInt(2),
                    datos.getFloat(3),datos.getInt(4), datos.getString(5)));
        }
        datos.close();
        return listaProducto;
    }
}

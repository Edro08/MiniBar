package com.purosurf.minibar.Presentador.Administrador.GestionProductos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Categoria;
import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.Interfaces.ISeleccionarProductoPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.ISeleccionarProducto_View;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarProductoPresentador implements ISeleccionarProductoPresentador {

    ISeleccionarProducto_View seleccionarProducto_view;

    public SeleccionarProductoPresentador(ISeleccionarProducto_View seleccionarProducto_view)
    {
        this.seleccionarProducto_view = seleccionarProducto_view;
    }

    @Override
    public List<Producto> listaProductos(Context context, String nombreCategoria) {
        //llenar lista
        List<Producto> listaProducto = new ArrayList<Producto>();
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT * FROM CATEGORIA WHERE NOMBRECATEGORIA = '" + nombreCategoria + "'";
        Cursor datos2 = base.rawQuery(consultaSql, null);
        datos2.moveToFirst();
        int IdCategoria = datos2.getInt(0);
        consultaSql = "SELECT * FROM PRODUCTO WHERE IDCATEGORIA = '" + IdCategoria + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        while(datos.moveToNext()){
            listaProducto.add(new Producto(datos.getInt(0), datos.getString(1), datos.getInt(2), (float) datos.getDouble(3), datos.getInt(4), datos.getString(5)));
        }
        return listaProducto;
    }

    public List<String> listaCategorias(Context context) {
        //llenar lista
        List<String> listaCategorias = new ArrayList<String>();
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT * FROM CATEGORIA";
        Cursor datos = base.rawQuery(consultaSql, null);

        while(datos.moveToNext()){
            listaCategorias.add(datos.getString(1));
        }

        return listaCategorias;
    }
}

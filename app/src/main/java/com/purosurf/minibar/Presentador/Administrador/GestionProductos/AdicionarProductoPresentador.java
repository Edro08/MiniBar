package com.purosurf.minibar.Presentador.Administrador.GestionProductos;

import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.Interfaces.IAdicionarProductoPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.IAdicionarProducto_View;

public class AdicionarProductoPresentador implements IAdicionarProductoPresentador {

    IAdicionarProducto_View iAdicionarProducto_view;

    public AdicionarProductoPresentador(IAdicionarProducto_View iAdicionarProducto_view) {
        this.iAdicionarProducto_view = iAdicionarProducto_view;
    }

    @Override
    public void AdicionarProducto(Context context, String nombre, String categoria, float precio, boolean estado, int minima, int maxima, String url) {
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        Cursor cursor = base.rawQuery("SELECT *FROM CATEGORIA WHERE NOMBRECATEGORIA =?",new String[]{categoria});
        int idcategoria;
        if(cursor.moveToFirst()){
            idcategoria = cursor.getInt(0);
            int idestado = estado ? 1: 2;
            String sql="INSERT INTO PRODUCTO (PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) " +
                    "VALUES('"+nombre+"', '"+idcategoria+"', '"+precio+"', '"+idestado+"','"+url+"');";
            base.execSQL(sql);
            Cursor producto = base.rawQuery("SELECT *FROM PRODUCTO WHERE PRODUCTONOMBRE =?",new String[]{nombre});
            if (producto.moveToFirst()){
                int idproducto = producto.getInt(0);
                sql = "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES" +
                        " ('"+idproducto+"', '"+minima+"', '"+maxima+"', 0);";
                base.execSQL(sql);
            }
        }

    }

    @Override
    public boolean verificarProducto(Context context, String nombreProducto) {
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getReadableDatabase();
        Cursor producto = base.rawQuery("SELECT *FROM PRODUCTO WHERE PRODUCTONOMBRE =?",new String[]{nombreProducto});
        if (producto.moveToFirst()){
            return true;
        } else {
            return false;
        }
    }
}

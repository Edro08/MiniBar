package com.purosurf.minibar.Presentador.Administrador.GestionProductos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.Interfaces.IActualizarProductoPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.IActualizarProducto_View;

import java.util.ArrayList;

public class ActualizarProductoPresentador implements IActualizarProductoPresentador {

    Producto producto;

    IActualizarProducto_View iActualizarProducto_view;

    public ActualizarProductoPresentador(IActualizarProducto_View iActualizarProducto_view) {
        this.iActualizarProducto_view = iActualizarProducto_view;
    }

    @Override
    public void obtenerProducto(Context context, int idproducto) {
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        Cursor cursor = base.rawQuery("SELECT" +
                                              " PRODUCTO.IDPRODUCTO, " +
                                              "PRODUCTO.PRODUCTONOMBRE," +
                                              " PRODUCTO.IDCATEGORIA," +
                                              " PRODUCTO.PRECIOUNITARIO," +
                                              " PRODUCTO.IDESTADO," +
                                              " PRODUCTO.IMAGENURL, " +
                                              " CATEGORIA.NOMBRECATEGORIA " +
                                              "FROM PRODUCTO INNER JOIN CATEGORIA ON " +
                                              "PRODUCTO.IDCATEGORIA = CATEGORIA.IDCATEGORIA" +
                                              "  WHERE IDPRODUCTO = ?"
                ,new String[]{Integer.toString(idproducto)});
        if (cursor.moveToFirst()){
            producto = new Producto(
              cursor.getInt(0),
              cursor.getString(1),
              cursor.getInt(2),
              cursor.getFloat(3),
              cursor.getInt(4),
              cursor.getString(5),
              cursor.getString(6)
            );
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

    public Producto getProducto() {
        return producto;
    }

    @Override
    public ArrayList<Integer> Cantidades(Context context){
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getReadableDatabase();
        ArrayList<Integer> lsCantidades = new ArrayList<Integer>();
        Cursor cursor = base.rawQuery("SELECT *FROM INVENTARIO WHERE IDPRODUCTO = ?", new String[]{Integer.toString(producto.getIdProducto())});
        if(cursor.moveToFirst()){
            lsCantidades.add(cursor.getInt(2));
            lsCantidades.add(cursor.getInt(3));
        }
        return lsCantidades;
    }

    @Override
    public void actualizarPoducto(Context context, int idproducto, String nombre, String categoria, float precio, boolean estado, int minima, int maxima, String url) {
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        Cursor cursor = base.rawQuery("SELECT *FROM CATEGORIA WHERE NOMBRECATEGORIA =?",new String[]{categoria});
        int idcategoria;
        if(cursor.moveToFirst()){
            idcategoria = cursor.getInt(0);
            int idestado = estado ? 1: 2;
            String sql="UPDATE PRODUCTO SET PRODUCTONOMBRE = '"+nombre+"', IDCATEGORIA='"+idcategoria+"', PRECIOUNITARIO ='"+precio+"', IDESTADO='"+idestado+"', IMAGENURL='"+url+"' WHERE IDPRODUCTO ="+idproducto;
            base.execSQL(sql);
            Cursor producto = base.rawQuery("SELECT *FROM PRODUCTO WHERE PRODUCTONOMBRE =?",new String[]{nombre});
            if (producto.moveToFirst()){
                sql = "UPDATE INVENTARIO SET CANTIDADMINIMA='"+minima+"', CANTIDADMAXIMA='"+maxima+"' WHERE IDPRODUCTO="+idproducto;
                base.execSQL(sql);
            }
        }
    }


}

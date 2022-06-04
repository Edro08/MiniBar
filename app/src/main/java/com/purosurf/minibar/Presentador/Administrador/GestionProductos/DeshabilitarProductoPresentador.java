package com.purosurf.minibar.Presentador.Administrador.GestionProductos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.Interfaces.IDeshabilitarProductoPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.IDeshabilitarProducto_View;

public class DeshabilitarProductoPresentador implements IDeshabilitarProductoPresentador {

     IDeshabilitarProducto_View iDeshabilitarProducto_view;

    public DeshabilitarProductoPresentador(IDeshabilitarProducto_View iDeshabilitarProducto_view) {
        this.iDeshabilitarProducto_view = iDeshabilitarProducto_view;
    }

    @Override
    public void ModificarEstadoProducto(Context context, int idproducto, int estado) {
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String sql="";
        sql="UPDATE PRODUCTO SET IDESTADO = '"+estado+"' WHERE IDPRODUCTO = "+idproducto;
        base.execSQL(sql);
    }

}

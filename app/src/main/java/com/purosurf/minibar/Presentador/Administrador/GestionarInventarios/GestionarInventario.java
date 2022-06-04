package com.purosurf.minibar.Presentador.Administrador.GestionarInventarios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Entrada;
import com.purosurf.minibar.Modelo.Inventario;
import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Modelo.Salida;
import com.purosurf.minibar.Presentador.Administrador.GestionarInventarios.Interfaces.IntrfcGestionInventarios;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.ISeleccionarProducto_View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GestionarInventario implements IntrfcGestionInventarios {

    ISeleccionarProducto_View iSeleccionarProducto_view;

    public GestionarInventario(ISeleccionarProducto_View iSeleccionarProducto_view) {
        this.iSeleccionarProducto_view = iSeleccionarProducto_view;
    }

    @Override
    public List<Inventario> listaProductos(Context context, String nombreCategoria) {
        //llenar lista
        List<Inventario> listaProducto = new ArrayList<Inventario>();
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT * FROM CATEGORIA WHERE NOMBRECATEGORIA = '" + nombreCategoria + "'";
        Cursor datos2 = base.rawQuery(consultaSql, null);
        datos2.moveToFirst();
        int IdCategoria = datos2.getInt(0);
        consultaSql = "SELECT INV.*, PRO.PRODUCTONOMBRE FROM INVENTARIO INV INNER JOIN PRODUCTO PRO ON PRO.IDPRODUCTO = INV.IDPRODUCTO WHERE IDCATEGORIA = '" + IdCategoria + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        while(datos.moveToNext()){
            listaProducto.add(new Inventario(datos.getInt(0),datos.getInt(1),datos.getInt(2),datos.getInt(3),datos.getInt(4),datos.getString(5)));
        }
        return listaProducto;
    }

    @Override
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

    @Override
    public Boolean ExisteReg(int idInventario) {
        return null;
    }

    @Override
    public Boolean InsertEntrada(Entrada entrada, Context context) {
        boolean exito = false;
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        try{
            String consultaSql;
            consultaSql = "INSERT INTO ENTRADA(IDUSUARIO, IDPRODUCTO, DESCRIPCION, FECHA, CANTIDAD, PRECIO, TOTAL) " +
                    "VALUES("+ entrada.getIdUsuario() +" ,"+ entrada.getIdProducto() +" ,'" + entrada.getDescripcion() + "' ,'" + entrada.getFecha()+
                    "' ,"+ entrada.getCantidad() + " ," + entrada.getPrecio() + " ," + entrada.getTotal() + ")";
            base.execSQL(consultaSql);
            exito = true;
        } catch (Exception e){

        }

        try {
            exito = false;
            String sqlUpdate = "";
            sqlUpdate = "UPDATE INVENTARIO SET EXISTENCIAS = EXISTENCIAS + " + entrada.getCantidad() + " WHERE IDPRODUCTO = " + entrada.getIdProducto();
            base.execSQL(sqlUpdate);
            exito = true;
        } catch (Exception e){

        }
        return exito;
    }

    @Override
    public Boolean InsertSalida(Salida salida, Context context) {
        boolean exito = false;
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        try{
            String consultaSql;
            consultaSql = "INSERT INTO SALIDA(IDUSUARIO, IDPRODUCTO, DESCRIPCION, FECHA, CANTIDAD, PRECIO, TOTAL) " +
                    "VALUES("+ salida.getIdUsuario() +" ,"+ salida.getIdUsuario() +" ,'" + salida.getDesccripcion() + "' ,'" + salida.getFecha()+
                    "' ,"+ salida.getCantidad() + " ," + salida.getPrecio() + " ," + salida.getTotal() + ")";
            base.execSQL(consultaSql);
            exito = true;
        } catch (Exception e){

        }

        try {
            exito = false;
            String sqlUpdate = "";
            sqlUpdate = "UPDATE INVENTARIO SET EXISTENCIAS = EXISTENCIAS - " + salida.getCantidad() + " WHERE IDPRODUCTO = " + salida.getIdProducto();
            base.execSQL(sqlUpdate);
            exito = true;
        } catch (Exception e){

        }
        return exito;
    }
}

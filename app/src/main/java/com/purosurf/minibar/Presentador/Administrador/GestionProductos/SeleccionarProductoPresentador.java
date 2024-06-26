package com.purosurf.minibar.Presentador.Administrador.GestionProductos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Categoria;
import com.purosurf.minibar.Modelo.Inventario;
import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.Interfaces.ISeleccionarProductoPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.ISeleccionarProducto_View;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarProductoPresentador implements ISeleccionarProductoPresentador {

    ISeleccionarProducto_View seleccionarProducto_view;

    Producto producto;

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
        consultaSql = "SELECT P.*, C.NOMBRECATEGORIA FROM PRODUCTO P INNER JOIN CATEGORIA C ON C.IDCATEGORIA = P.IDCATEGORIA WHERE P.IDCATEGORIA = '" + IdCategoria + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        while(datos.moveToNext()){
            listaProducto.add(new Producto(datos.getInt(0), datos.getString(1), datos.getInt(2), (float) datos.getDouble(3), datos.getInt(4), datos.getString(5), datos.getString(6)));
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

    @Override
    public Boolean InsertarProd_Ivnt(Producto producto, Context context, Inventario inventario) {
        boolean exito = false;
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        try{
            String consultaSql;
            consultaSql = "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) " +
                    "VALUES('"+ producto.getProductoNombre() +"' ,"+ producto.getIdCategoria() +" ," + producto.getPrecioUnitario() +
                    "," + 1 + " , '"+ producto.getImagenURL() + "')";
            base.execSQL(consultaSql);
            exito = true;
        } catch (Exception e){

        }

        try {
            exito = false;
            String consultaSql;
            consultaSql = "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) " +
                    "VALUES("+ inventario.getIdProducto() +" ,"+ inventario.getCantidadMinima() +" ," + inventario.getCantidadMaxima() +
                    "," + 0 + "')";
            base.execSQL(consultaSql);
            exito = true;
        } catch (Exception e){

        }
        return exito;
    }

    @Override
    public void cargarProducto(Context context, int idproducto) {
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
    public List<Integer> cargarCantidades(Context context) {
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

}

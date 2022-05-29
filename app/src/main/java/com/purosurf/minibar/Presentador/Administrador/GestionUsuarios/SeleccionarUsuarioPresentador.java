package com.purosurf.minibar.Presentador.Administrador.GestionUsuarios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Usuario;
import com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.Interfaces.ISeleccionarUsuarioPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionUsuarios.Interfaces.ISeleccionarUsuario_View;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarUsuarioPresentador implements ISeleccionarUsuarioPresentador {

    ISeleccionarUsuario_View seleccionarUsuario_view;

    public SeleccionarUsuarioPresentador(ISeleccionarUsuario_View seleccionarUsuario_view)
    {
        this.seleccionarUsuario_view = seleccionarUsuario_view;
    }

    @Override
    public List<Usuario> listaUsuarios(Context context) {
        //llenar lista
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT * FROM USUARIO";
        Cursor datos = base.rawQuery(consultaSql, null);

        while(datos.moveToNext()){
            listaUsuarios.add(new Usuario(datos.getInt(0), datos.getString(1), datos.getString(2), datos.getInt(3), datos.getInt(4)));
        }

        return listaUsuarios;
    }
}

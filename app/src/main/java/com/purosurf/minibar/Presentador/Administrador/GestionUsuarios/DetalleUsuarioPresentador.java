package com.purosurf.minibar.Presentador.Administrador.GestionUsuarios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Usuario;
import com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.Interfaces.IDetalleUsuarioPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionUsuarios.Interfaces.IDetalleUsuario_View;

import java.util.ArrayList;
import java.util.List;

public class DetalleUsuarioPresentador implements IDetalleUsuarioPresentador {

    IDetalleUsuario_View iDetalleUsuario_view;

    public DetalleUsuarioPresentador (IDetalleUsuario_View iDetalleUsuario_view)
    {
        this.iDetalleUsuario_view = iDetalleUsuario_view;
    }

    @Override
    public Cursor DatosPersonaUsuario(Context context, int idUsuario) {
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT IDUSUARIO, USUARIO, IDESTADO, NOMBRE, CORREOELECTRONICO " +
                "FROM USUARIO INNER JOIN PERSONA ON USUARIO.IDPERSONA = PERSONA.IDPERSONA " +
                "WHERE IDUSUARIO = '" + idUsuario + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        return datos;
    }
}

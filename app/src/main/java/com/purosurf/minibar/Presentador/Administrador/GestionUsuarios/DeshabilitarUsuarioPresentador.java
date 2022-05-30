package com.purosurf.minibar.Presentador.Administrador.GestionUsuarios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Usuario;
import com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.Interfaces.IDeshabilitarUsuarioPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionUsuarios.Interfaces.IDeshabilitarUsuario_Viw;

import java.util.ArrayList;
import java.util.List;

public class DeshabilitarUsuarioPresentador implements IDeshabilitarUsuarioPresentador {

    IDeshabilitarUsuario_Viw iDeshabilitarUsuario_viw;

    public DeshabilitarUsuarioPresentador(IDeshabilitarUsuario_Viw iDeshabilitarUsuario_viw){
        this.iDeshabilitarUsuario_viw = iDeshabilitarUsuario_viw;
    }

    @Override
    public List<Usuario> DatosUsuario(Context context, int idUsuario) {
        List<Usuario> DatosUsuario = new ArrayList<Usuario>();
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT * FROM USUARIO WHERE IDUSUARIO = '" + idUsuario + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        datos.moveToFirst();
        DatosUsuario .add(new Usuario(datos.getInt(0), datos.getString(1), datos.getString(2), datos.getInt(3), datos.getInt(4)));
        return DatosUsuario ;
    }

    @Override
    public void ActivarDesactivarUsuario(Context context, int idUsuario, boolean estado) {
        int idEstado;
        if(estado)
            idEstado = 1;
        else
            idEstado = 2;

        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "UPDATE USUARIO SET IDESTADO = '" + idEstado + "' WHERE IDUSUARIO = '" + idUsuario + "'";
        base.execSQL(consultaSql);
    }
}
